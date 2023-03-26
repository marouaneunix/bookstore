package ma.norsys.bookstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ma.norsys.bookstore.BookstoreApplication;
import ma.norsys.bookstore.entity.Book;
import ma.norsys.bookstore.repository.BookRepository;
import ma.norsys.bookstore.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = BookstoreApplication.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    BookService bookService;

    private Book firstBook, book;

    @BeforeEach
    void setUp() {
        firstBook = new Book(1, "d4251", "Java", "Smith", "Coding", "learning code with java");
        book = new Book(2, "j4251", "Docker", "Jhon", "DevOps", "Creating containers");
    }

    @Test
    public void shouldAddBook() throws Exception {
        String uri = "/api/books";
        String expectedJson = objectMapper
                .writeValueAsString(book);
        this.mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedJson))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("isbn").value(book.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value(book.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("description").value(book.getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("author").value(book.getAuthor()))
                .andExpect(MockMvcResultMatchers.jsonPath("category").value(book.getCategory()));
    }

    @Test
    public void shouldGetAllBooks() throws Exception {
        String uri="/api/books";
        List<Book> listBook = new ArrayList<>();
        listBook.add(firstBook);
        listBook.add(book);
        this.mockMvc.perform(MockMvcRequestBuilders.get(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].isbn").value(firstBook.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(firstBook.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value(firstBook.getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author").value(firstBook.getAuthor()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].category").value(book.getCategory()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].isbn").value(book.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value(book.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].description").value(book.getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].author").value(book.getAuthor()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].category").value(book.getCategory()));
    }

    @Test
    public void shouldDeleteBook() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/books/{id}",1))
                .andExpect(status().isNoContent());
    }



}
