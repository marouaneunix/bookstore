package ma.norsys.bookstore;

import ma.norsys.bookstore.entity.Book;
import ma.norsys.bookstore.repository.BookRepository;
import ma.norsys.bookstore.service.BookService;
import ma.norsys.bookstore.service.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {


    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService = new BookServiceImpl(bookRepository);


    @Test
    public void should_return_list_of_books() {
        List<Book> expectedBooks = List.of(new Book(1L,"Fundamentals of Wavelets","Goswami","samir","2003","Category","dec"));
        when(bookRepository.findAll()).thenReturn(expectedBooks);

        List<Book> books = bookService.getAllBooks();

        assertThat(books).hasSize(1);
        assertThat(books).extracting("title").containsOnly("Fundamentals of Wavelets");

        verify(bookRepository).findAll();
    }

    @Test
    public void should_throw_exception_when_book_is_null() {
        assertThrows(
                NullPointerException.class,
                () -> bookService.addBook(null),
                "Book should not be null"
        );
    }

    @Test
    public void should_throw_exception_when_book_name_is_null() {
        Book book = new Book(1L,null,"Goswami","samir","2003","Category","dec");
        assertThrows(
                NullPointerException.class,
                () -> bookService.addBook(book),
                "Book name should not be null"
        );
    }

    @Test
    public void should_save_book() {
        Book book = new Book();
        book.setTitle("la boite a mervielle");
        book.setIsbn("DKMJHT");

        bookService.addBook(book);
        ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(bookArgumentCaptor.capture());

        assertThat(bookArgumentCaptor.getValue())
                .extracting("title")
                .isEqualTo("la boite a mervielle");
        assertThat(bookArgumentCaptor.getValue())
                .extracting("isbn")
                .isEqualTo("DKMJHT");
    }
}