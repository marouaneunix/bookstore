package ma.norsys.bookstore;

import ma.norsys.bookstore.models.Book;
import ma.norsys.bookstore.repositories.BookRepository;
import ma.norsys.bookstore.services.BookServiceImp;
import ma.norsys.bookstore.services.IBookService;
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
class BookServiceTests {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private IBookService bookService=new BookServiceImp();

    @Test
    public void shouldGetAllBooks() {

        List<Book> expectedBooks = List.of( new Book (0,"L'Alchimiste","dkcyd","Paulo Coelho","L'Alchimiste est un conte philosophique de Paulo Coelho paru en 1988.","Classics"));
        when(bookRepository.findAll()).thenReturn(expectedBooks);

        List<Book> books = bookService.getAll();

        assertThat(books).hasSize(1);
        assertThat(books).extracting("title").containsOnly("L'Alchimiste");

        verify(bookRepository).findAll();
    }

    @Test
    public void shouldAddBook() {
        Book book = new Book (0,"L'Alchimiste","dkcyd","Paulo Coelho","L'Alchimiste est un conte philosophique de Paulo Coelho paru en 1988.","Classics");

        bookService.addBook(book);
        ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(bookArgumentCaptor.capture());

        assertThat(bookArgumentCaptor.getValue())
                .extracting("title")
                .isEqualTo("L'Alchimiste");
    }


}
