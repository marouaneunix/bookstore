package ma.norsys.bookstore;

import ma.norsys.bookstore.models.Book;
import ma.norsys.bookstore.repositories.BookRepository;
import ma.norsys.bookstore.services.BookServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImp bookService;

    @Test
    public void should_return_list_of_books() {
        //arrange
        List<Book> expectedBooks = List.of(new Book(1, "Les Sept Habitudes des gens efficaces", "L1234", "Stephen Covey", "aaaaaaaaaaa", "science vie"),
                new Book(2, "Harry Potter and the Prisoner of Azkaban", "K4567", "J.K. Rowling", "bbbbbbbbbbbbb", "dcience"),
                new Book(3, "Spring Boot", "K985", "J.K. Rowling", "cccccccccccc", "drama Fantasy"));
        when(bookRepository.findAll()).thenReturn(expectedBooks);

        //act
        List<Book> books = bookService.getAll();

        //assert
        assertThat(books).hasSize(3);
        expectedBooks.forEach((bookExpected) -> {
            assertThat(books)
                    .filteredOn(book -> book.getTitle().equals(bookExpected.getTitle()));
        });
        verify(bookRepository).findAll();
    }

    @Test
    public void should_return_book_withId() {
        //arrange
        Book expectedBook = new Book(1, "Les Sept Habitudes des gens efficaces", "L1234", "Stephen Covey", "aaaaaaaaaaa", "science vie");

        when(bookRepository.findById(1)).thenReturn(Optional.of(expectedBook));

        //act
        Book book = bookService.getById(1);

        //assert
        assertThat(book).extracting("title").isEqualTo("Les Sept Habitudes des gens efficaces");
    }


    @Test
    public void should_save_book() {
        Book book = new Book(1, "Les Sept Habitudes des gens efficaces", "L1234", "Stephen Covey", "aaaaaaaaaaa", "science vie");
        bookService.addBook(book);
        ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(bookArgumentCaptor.capture());

        assertThat(bookArgumentCaptor.getValue())
                .extracting("title")
                .isEqualTo("Les Sept Habitudes des gens efficaces");
    }

    @Test
    public void should_throw_exception_when_book_is_null() {
        assertThrows(
                NullPointerException.class,
                () -> bookService.addBook(null),
                "all properties are required"
        );
    }


    @Test
    public void should_throw_exception_when_OnePropertyOfBook_is_null() {
        List<Book> books = List.of(new Book(1, null, "L1234", "Stephen Covey", "aaaaaaaaaaa", "science vie"),
                new Book(2, "Harry Potter and the Prisoner of Azkaban", null, "J.K. Rowling", "bbbbbbbbbbbbb", "dcience"),
                new Book(3, "Spring Boot", "K985", null, "cccccccccccc", "drama Fantasy"),
                new Book(3, "Spring Boot", "K985", "null", "cccccccccccc", null)
        );

        books.forEach((b) -> {
            assertThrows(
                    NullPointerException.class,
                    () -> bookService.addBook(b),
                    "all properties are required"
            );
        });
    }


}
