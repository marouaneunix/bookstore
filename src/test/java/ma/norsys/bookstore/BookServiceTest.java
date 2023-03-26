package ma.norsys.bookstore;

import ma.norsys.bookstore.exception.BookNotFoundException;
import ma.norsys.bookstore.model.Book;
import ma.norsys.bookstore.repository.BookRepository;
import ma.norsys.bookstore.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
//import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    
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
        Book book = new Book(1L, null,"Maroua","12345687","computer_science","asdfghjkl");
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
        book.setAuthor("William");
        book.setCategories("Self_development");
        book.setIsbn("1234-567890");
        book.setDescription("Atomic Habbit");

        bookService.save(book);
        ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(bookArgumentCaptor.capture());

        assertThat(bookArgumentCaptor.getValue())
                .extracting("name")
                .isEqualTo("Atomic Habbit");
    }

    @Test
    public void whenGetAll_thenStatus200() throws Exception {
        List<Book> expectedBooks = List.of(
                new Book(1,"Java","Maroua","12345687","computer_science","asdfghjkl"),
                new Book(2,"Java1","Maroua","computer_science","0987654567890","asdfghjkl")
        );
        when(bookService.getAll()).thenReturn(expectedBooks);
        List<Book> books = bookService.getAll();
        assertThat(books).hasSize(2);
        assertThat(books).extracting("name").contains("Java","Java1");

        verify(bookRepository).findAll();
    }
    @Test
    public void givenBookId_whenGetBookById_thenStatus200() throws BookNotFoundException {
        Book book1=new Book(1,"atomic hbaits","James Clear","self_developement","1234","Atomic Habits by James Clear is another must-read book if you have adult ADHD. We used to think that setting huge goals and taking what many call massive action was the key to success.");
        when(bookService.getBookById(1L)).thenReturn(Optional.of(book1));
        Optional<Book> savedBook = bookService.getBookById(1L);
        assertThat(savedBook).isNotNull();
        verify(bookRepository).findById(1L);
    }








}
