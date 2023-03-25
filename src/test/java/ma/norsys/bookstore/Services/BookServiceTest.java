package ma.norsys.bookstore.Services;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;

import ma.norsys.bookstore.Exceptions.BookNotFoundException;
import ma.norsys.bookstore.Models.Book;
import ma.norsys.bookstore.Repository.BookRepository;
import ma.norsys.bookstore.Services.Impl.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BookServiceTest {


    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void setUp() {
        bookRepository.deleteAll();
        Book book1 = Book.builder()
                .title("livre1")
                .auteur("mouad")
                .isbn("1")
                .categories("fantasy young adult")
                .description("desc1")
                .build();
        bookRepository.save(book1);

        Book book2 = Book.builder()
                .title("livre2")
                .auteur("Harper Lee")
                .isbn("2")
                .categories("classic historical fiction")
                .description("desc2")
                .build();
        bookRepository.save(book2);

        Book book3 = Book.builder()
                .title("1984")
                .auteur("auteur4")
                .isbn("3")
                .categories("classic dystopian")
                .description("desc4")
                .build();
        bookRepository.save(book3);
    }


    @Test
    public void testAddBook() {
        Book book4 = Book.builder()
                .title("livre4")
                .auteur("samir")
                .categories("classic")
                .isbn("4")
                .description("desc4")
                .build();

        Book addedBook = bookService.addBook(book4);
        assertNotNull(addedBook.getId());
        assertEquals(book4.getTitle(), addedBook.getTitle());
        assertEquals(book4.getAuteur(), addedBook.getAuteur());
        assertEquals(book4.getCategories(), addedBook.getCategories());
        assertEquals(book4.getDescription(), addedBook.getDescription());



    }

    @Test
    public void testGetAllBooks() {
        List<Book> allBooks = bookService.getAllBooks();

        assertNotNull(allBooks);
        assertEquals(3, allBooks.size());
    }


    @Test
    public void testFindBookByTitle() throws BookNotFoundException {
        List<Book> foundBooks = bookService.findBookByTitle("livre");

        assertNotNull(foundBooks);
        assertEquals(2, foundBooks.size());

        for (Book book : foundBooks) {
            assertTrue(book.getTitle().startsWith("livre"));
        }

        assertThrows(BookNotFoundException.class, () -> bookService.findBookByTitle("non-existing-title"));
    }


    @Test
    public void testGetAllBookByCategoryContaining() {
        HashSet<Book> foundBooks = bookService.getAllBookByCategoryContaining("classic");

        assertNotNull(foundBooks);
        assertEquals(2, foundBooks.size());

        foundBooks = bookService.getAllBookByCategoryContaining("dystopian");
        assertNotNull(foundBooks);
        assertEquals(1, foundBooks.size());

        foundBooks = bookService.getAllBookByCategoryContaining("young adult");
        assertNotNull(foundBooks);
        assertEquals(1, foundBooks.size());
    }



    }
