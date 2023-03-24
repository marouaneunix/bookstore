package ma.norsys.bookstore.Book;

import ma.norsys.bookstore.entities.Book;
import ma.norsys.bookstore.exception.BookNotFoundException;
import ma.norsys.bookstore.repository.BookRepository;
import ma.norsys.bookstore.services.BookService;
import ma.norsys.bookstore.services.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService=new
            BookServiceImpl();

    @Test
    public void should_return_list_of_books() {
        //arrange
        List<Book> expectedBooks = List.of(new Book(1L, "The Great Gatsby","978-3-16-148410-0","F. Scott Fitzgerald","Tragedy"));
        Mockito.when(bookRepository.findAll()).thenReturn(expectedBooks);

        //act
        List<Book> books = bookService.getAllBooks();

        //assert
        assertThat(books).hasSize(1);
        assertThat(books).extracting("name").containsOnly("The Great Gatsby");


    }

    @Test
    public void should_throw_exception_when_book_is_null() {
        assertThrows(
                NullPointerException.class,
                () -> bookService.createBook(null),
                "this book is not exist"
        );
    }

    @Test
    public void should_throw_exception_when_book_name_is_null() {
        Book book = new Book(1L, null,"978-3-16-148410-0","F. Scott Fitzgerald","Tragedy");
        assertThrows(
                NullPointerException.class,
                () -> bookService.createBook(book),
                "Book name should not be null"
        );
    }

    @Test
    public void should_save_book() {
        Book book = new Book();
        book.setName("Atomic Habbit");


        bookService.createBook(book);
        ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(bookArgumentCaptor.capture());

        assertThat(bookArgumentCaptor.getValue())
                .extracting("name")
                .isEqualTo("Atomic Habbit");
    }

}
