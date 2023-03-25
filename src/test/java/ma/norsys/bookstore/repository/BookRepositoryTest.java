package ma.norsys.bookstore.repository;

import ma.norsys.bookstore.models.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;


@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void beforeEach() {
        Book book = new Book();
        book.setName("Book 1");
        book.setAuthor("Marouane");
        book.setCategory("Fiction");
        book.setIsbn("29kkf29j");
        book.setDescription("This is the book description");

        bookRepository.save(book);
    }


    @Test
    public void testCreateBook() {
        Book savedBook = bookRepository.findAll().get(0);

        assertNotNull(savedBook);
        assertNotNull(savedBook.getId());
        assertEquals("Book name should be 'Book 1'", savedBook.getName(), "Book 1");
        assertEquals("Book author should be 'Marouane'", "Marouane", savedBook.getAuthor());
    }


    @Test
    public void testFindBookById() {
        Optional<Book> book = bookRepository.findById(1);

        assertTrue(book.isPresent());
        assertEquals("Book name should be 'Book 1'", book.get().getName(), "Book 1");
        assertEquals("Book category should be 'Fiction'", book.get().getCategory(), "Fiction");
    }


    @Test void testFindByNameContainingAndCategoryContaining() {
        List<Book> books = bookRepository.findByNameContainingAndCategoryContaining("", "");

        assertThat(books.size(), equalTo(1));
    }


    @Test
    public void testDeleteBook() {
        bookRepository.deleteById(1);

        Optional<Book> book = bookRepository.findById(1);
        assertFalse(book.isPresent());
    }

    @AfterEach
    public void afterEach() {
        bookRepository.deleteById(1);
    }
}







