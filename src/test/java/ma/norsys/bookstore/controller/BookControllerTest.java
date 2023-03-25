package ma.norsys.bookstore.controller;

import jakarta.transaction.Transactional;
import ma.norsys.bookstore.models.Book;
import ma.norsys.bookstore.repository.BookRepository;
import ma.norsys.bookstore.service.BookService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource("/application.properties")
@AutoConfigureMockMvc
@Transactional
@SpringBootTest
public class BookControllerTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookController bookController;

    @Autowired
    private BookRepository bookRepository;


    @Mock
    BookService bookServiceMock;

    @Value("${sql.script.create.book1}")
    private String sqlCreateBook1;

    @Value("${sql.script.create.book2}")
    private String sqlCreateBook2;

    @Value("${sql.script.create.book3}")
    private String sqlCreateBook3;

    @Value("${sql.script.delete.books}")
    private String sqlDeleteBooks;


    @BeforeEach
    public void setupDatabase() {
        jdbc.execute(sqlCreateBook1);
        jdbc.execute(sqlCreateBook2);
        jdbc.execute(sqlCreateBook3);
    }

    @Test
    public void testGetBooksHttpRequest() throws Exception {
        Book book1 = new Book(1, "Book 1","Marouane","dfk2542d","Fiction","");
        Book book2 = new Book(2, "Book 2","Marouane","ad323jk2","Crime","");

        List<Book> books = new ArrayList<>(List.of(book1, book2));

        when(bookServiceMock.getBooks()).thenReturn(books);

        assertEquals("Book 1", bookController.getBooks().get(0).getName(), "Name should be 'Book 1'");

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/books"))
                .andExpect(status().isOk()).andReturn();
    }


    @Test
    public void testFindBookById() throws Exception {
        Book book = new Book(1, "Book 1","Marouane","dfk2542d","Fiction","");

        when(bookServiceMock.findById(1)).thenReturn(Optional.of(book));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/books/1"))
                .andExpect(status().isOk());

        assertEquals(bookController.getBook(1).get().getName(), "Book 1", "Name should be 'Book 1'");
        assertEquals(bookController.getBook(1).get().getCategory(), "Fiction", "Category should be 'Fiction'");
    }


    @Test
    public void testGetBooksByNameAndCategory() throws Exception {
        List<Book> books = bookRepository.findAll();
        when(bookServiceMock.findByNameAndCategory("", "")).thenReturn(books);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/books"))
                .andExpect(status().isOk());

        assertEquals(bookController.getBooksByNameAndCategory("", "").get(0).getName(), "Book 1", "Name should be 'Book 1'");
        assertEquals(bookController.getBooksByNameAndCategory("", "").get(1).getName(), "Book 2", "Name should be 'Book 2'");
    }


    @Test
    public void testCreateBook() {
        Book book = new Book();
        book.setName("Book");
        book.setAuthor("Marouane");
        book.setCategory("War");
        book.setIsbn("234kjk5k2");
        book.setDescription("This is the Book description");

        when(bookServiceMock.createProduct(book)).thenReturn(book);

        assertEquals("Book", bookController.createBook(book).getName(), "Name should be 'Book'");
    }


    @Test
    public void testDeleteBook() throws Exception {
        assertTrue(bookRepository.findById(1).isPresent());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/books/{bookId}", 1))
                .andExpect(status().isOk());

        assertFalse(bookRepository.findById(1).isPresent());

        assertEquals(2, bookController.deleteBook(2));

    }




    @AfterEach
    public void setupAfterTransaction() {
        jdbc.execute(sqlDeleteBooks);
    }



}












