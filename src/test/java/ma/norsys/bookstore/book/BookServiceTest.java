package ma.norsys.bookstore.book;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
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
    private BookService bookService;

    @Test
    public void should_return_list_of_books() {
        //arrange
        List<Book> expectedBooks = List.of(new Book(1L, "Java"));
        when(bookRepository.findAll()).thenReturn(expectedBooks);

        //act
        List<Book> books = bookService.findAll();

        //assert
        assertThat(books).hasSize(1);
        assertThat(books).extracting("name").containsOnly("Java");

        verify(bookRepository).findAll();
    }

    @Test
    public void should_throw_exception_when_book_is_null() {
        assertThrows(
                NullPointerException.class,
                () -> bookService.save(null),
                "Book should not be null"
        );
    }

    @Test
    public void should_throw_exception_when_book_name_is_null() {
        Book book = new Book(1L, null);
        assertThrows(
                NullPointerException.class,
                () -> bookService.save(book),
                "Book name should not be null"
        );
    }

    @Test
    public void should_save_book() {
        Book book = new Book();
        book.setName("Atomic Habbit");


        bookService.save(book);
        ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(bookArgumentCaptor.capture());

        assertThat(bookArgumentCaptor.getValue())
                .extracting("name")
                .isEqualTo("Atomic Habbit");
    }
}
