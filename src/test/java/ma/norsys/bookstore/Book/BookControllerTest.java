package ma.norsys.bookstore.Book;

import com.fasterxml.jackson.databind.ObjectMapper;
import ma.norsys.bookstore.BookstoreApplication;
import ma.norsys.bookstore.entities.Book;
import ma.norsys.bookstore.exception.BookNotFoundException;
import ma.norsys.bookstore.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@SpringBootTest(classes = BookstoreApplication.class)
@ExtendWith(SpringExtension.class)
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
    }

    @Test
    public void testGetAllBooks() throws Exception {
        Book book1=new Book(null, "The Great Gatsby","978-3-16-148410-0","F. Scott Fitzgerald","Tragedy");
        Book book2 = new Book(null, "To Kill a Mockingbird", "978-0-446-31078-9", "Harper Lee", "Fiction");
        List<Book> userList = Arrays.asList(book1, book2);

        bookRepository.save(book1);
        bookRepository.save(book2);

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("The Great Gatsby"))
                .andExpect(jsonPath("$[0].isbn").value("978-3-16-148410-0"))
                .andExpect(jsonPath("$[1].name").value("To Kill a Mockingbird"))
                .andExpect(jsonPath("$[1].isbn").value("978-0-446-31078-9"));
    }


    @Test
    public void testGetBook() throws Exception , BookNotFoundException {
        Book book=new Book(7L, "The Great Gatsby","978-3-16-148410-0","F. Scott Fitzgerald","Tragedy");
        bookRepository.save(book);
        mockMvc.perform(get("/books/7"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("The Great Gatsby"))
                .andExpect(jsonPath("$.isbn").value("978-3-16-148410-0"));
    }



    @Test
    public void testSaveBook() throws Exception {
        Book book =new Book(null, "The Great Gatsby","978-3-16-148410-0","F. Scott Fitzgerald","Tragedy");

        bookRepository.save(book);

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("The Great Gatsby"))
                .andExpect(jsonPath("$.isbn").value("978-3-16-148410-0"));
    }

    @Test
    public void testUpdateBook() throws Exception {
        // arranged
        Book book =new Book(null, "The Great Gatsby","978-3-16-148410-0","F. Scott Fitzgerald","Tragedy");
        //act
        bookRepository.save(book);
        //assert
        mockMvc.perform(put("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("The Great Gatsby"))
                .andExpect(jsonPath("$.isbn").value("978-3-16-148410-0"));
    }


    @Test
    void testGetBookById_thenThrowException() throws Exception, BookNotFoundException {


        mockMvc.perform(get("/books/22")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("this book is not exist")));
    }
    @Test
    void testSearchBooks() throws Exception {
        Book book1 = new Book(null, "The Great Gatsby","978-3-16-148410-0","F. Scott Fitzgerald","Tragedy");
        Book book2 = new Book(null, "To Kill a Mockingbird Great", "978-0-446-31078-9", "Harper Lee", "Fiction");

        bookRepository.save(book2);
        bookRepository.save(book1);

        // Mock the behavior of the bookService
        bookRepository.findBooksByNameContainingIgnoreCaseAndCategorieContainingIgnoreCase("great", "tragedy");

        mockMvc.perform(get("/books/search")
                        .param("name", "great")
                        .param("categories", "tragedy,Fiction"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(book1.getId()))
                .andExpect(jsonPath("$.[0].name").value(book1.getName()))
                .andExpect(jsonPath("$.[0].isbn").value(book1.getISBN()))
                .andExpect(jsonPath("$.[0].author").value(book1.getAuthor()))
                .andExpect(jsonPath("$.[0].categorie").value(book1.getCategorie()))
                .andExpect(jsonPath("$.[1].id").value(book2.getId()))
                .andExpect(jsonPath("$.[1].name").value(book2.getName()))
                .andExpect(jsonPath("$.[1].isbn").value(book2.getISBN()))
                .andExpect(jsonPath("$.[1].author").value(book2.getAuthor()))
                .andExpect(jsonPath("$.[1].categorie").value(book2.getCategorie()));
    }

}
