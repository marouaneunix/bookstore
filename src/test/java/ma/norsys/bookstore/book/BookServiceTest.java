package ma.norsys.bookstore.book;


import ma.norsys.bookstore.Entities.Book;
import ma.norsys.bookstore.Repositories.BookRepository;
import ma.norsys.bookstore.Services.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.mockito.ArgumentCaptor;

import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    public void should_return_list_of_books() {
        //arrange
        List<Book> expectedBooks = List.of(new Book(1L, "Java","comedy horror","mouad","123-14","the best book ever"));
        when(bookRepository.findAll()).thenReturn(expectedBooks);

        //act
        List<Book> books = bookService.getAllBooks();

        //assert
        assertThat(books).hasSize(1);
        assertThat(books).extracting("name").containsOnly("Java");

        verify(bookRepository).findAll();
    }

    @Test
    public void should_throw_exception_when_book_is_null() {
        assertThrows(
                NullPointerException.class,
                () -> bookService.saveBook(null),
                "Book should not be null"
        );
    }

    @Test
    public void should_throw_exception_when_book_name_is_null() {
        Book book = new Book(1L, null,"comedy horror","mouad","123-14","the best book ever");
        assertThrows(
                NullPointerException.class,
                () -> bookService.saveBook(book),
                "Book name should not be null"
        );
    }

    @Test
    public void should_save_book() {
        Book book = new Book();
            book.setAuthor("mouad");
            book.setCategory("comedy horror");
            book.setDescription("the best book ever");
            book.setIsbn("123-14");
            book.setName("java");

        bookService.saveBook(book);
        ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(bookArgumentCaptor.capture());

        assertThat(bookArgumentCaptor.getValue())
                .extracting("name")
                .isEqualTo("java");
        assertThat(bookArgumentCaptor.getValue())
                .extracting("category")
                .isEqualTo("comedy horror");
        assertThat(bookArgumentCaptor.getValue())
                .extracting("description")
                .isEqualTo("the best book ever");
        assertThat(bookArgumentCaptor.getValue())
                .extracting("isbn")
                .isEqualTo("123-14");
        assertThat(bookArgumentCaptor.getValue())
                .extracting("author")
                .isEqualTo("mouad");
    }
}
