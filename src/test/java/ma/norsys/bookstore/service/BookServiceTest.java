package ma.norsys.bookstore.service;

import jakarta.transaction.Transactional;
import ma.norsys.bookstore.models.Book;
import ma.norsys.bookstore.repository.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestPropertySource("/application.properties")
@Transactional
@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JdbcTemplate jdbc;

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
    public void testGetAllBooks() {

        List<Book> books = bookService.getBooks();

        assertThat(books.size(), equalTo(3));
    }

    @Test
    public void testFindBookById() {
        Optional<Book> book = bookService.findById(1);
        assertTrue(book.isPresent());

        Book book1 = book.get();
        assertThat(book1.getName(), equalTo("Book 1"));
    }


    @Test
    public void testAddBook() {
        // add book
        Book book = new Book();
        book.setName("Book 4");
        book.setAuthor("Marouane");
        book.setIsbn("dfk2542d");
        book.setCategory("Crime");
        book.setDescription("This is the book description");
        bookService.createProduct(book);

        // the size of the books list should be 4
        List<Book> books = bookService.getBooks();
        assertThat(books.size(), equalTo(4));
        assertThat(books.get(3).getName(), equalTo("Book 4"));


    }


    @Test
    public void testFindByNameAndCategory() {
        List<Book> books = bookService.findByNameAndCategory("Book", "");
        assertThat(books.size(), equalTo(3));

        List<Book> books1 = bookService.findByNameAndCategory("Book 1", "Fiction");
        assertThat(books1.size(), equalTo(1));
        assertThat(books1.get(0).getAuthor(), equalTo("Marouane"));
    }



    @Test
    public void testDeleteBook() {
        // delete the book
        bookService.deleteBookById(1);

        // verify that the book was deleted
        Optional<Book> deletedBook = bookRepository.findById(1);
        assertFalse(deletedBook.isPresent());
    }



    @AfterEach
    public void setupAfterTransaction() {
        jdbc.execute(sqlDeleteBooks);
    }
}







