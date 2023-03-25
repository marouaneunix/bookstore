package ma.norsys.bookstore;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import ma.norsys.bookstore.entity.Book;
import ma.norsys.bookstore.service.BookServiceImpl;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookServiceImpl bookService;

    private Book firstBook, secondBook;

    @BeforeEach
    void init() {
        firstBook = new Book(1, "123-5444", "science_de_vie", "John", "science",
                "science");
        secondBook = new Book(2, "123679", "science_de_terre", "Been", "science",
                "science de terre");
    }

    @Test
    void whenGetAllBook_thenStatus200() throws Exception {
        List<Book> listbook = new ArrayList<>();
        listbook.add(secondBook);
        listbook.add(firstBook);

        bookService.getAllBook();

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", is(listbook.size())));
    }

    @Test
    void givenExistentBookName_whenGetByName_thenStatus200() throws Exception {

        List<Book> listBook = new ArrayList<>();
        listBook.add(firstBook);

        bookService.getBookByName(firstBook.getName());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/books/search")
                .param("name", firstBook.getName())
                .param("category", "")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void givenExistentBookCategory_whenGetByCategory_thenStatus200() throws Exception {
        List<Book> listBook = new ArrayList<>();
        listBook.add(firstBook);
        listBook.add(secondBook);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/books/search")
                .param("name", "")
                .param("category", firstBook.getCategory())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void givenExistentBookNameAndCategory_whenGetByNameAndCategory_thenStatus200() throws Exception {

        List<Book> listBook = new ArrayList<>();
        listBook.add(firstBook);
        listBook.add(secondBook);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/books/search")
                .param("name", firstBook.getName())
                .param("category", secondBook.getCategory())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
