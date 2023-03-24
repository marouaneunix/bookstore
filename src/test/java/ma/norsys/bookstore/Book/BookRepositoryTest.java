package ma.norsys.bookstore.Book;


import ma.norsys.bookstore.entities.Book;
import ma.norsys.bookstore.repository.BookRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.lang.constant.ClassDesc.of;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;

    @ParameterizedTest
    @ValueSource(strings = {"The", "the", "THE"})
    public void should_find_search_by_name(String name) {
        Book book1 = new Book(1L, "The Great Gatsby","978-3-16-148410-0","F. Scott Fitzgerald","Tragedy");
        Book book2 = new Book(2L, "To Kill a Mockingbird", "978-0-446-31078-9", "Harper Lee", "Fiction");
        Book book3 = new Book(3L, "Pride and Prejudice", "978-0-14-143951-8", "Jane Austen", "Romance");
        Book book4 = new Book(4L, "The Catcher in the Rye", "978-0-316-76948-8", "J.D. Salinger", "Coming of Age");
        List<Book> savedBooks =new ArrayList<>();
        savedBooks.add(book1);
        savedBooks.add(book2);
        savedBooks.add(book3);
        savedBooks.add(book4);


        bookRepository.saveAll(savedBooks);
        Set<Book> books = bookRepository.findByNameIgnoreCaseContaining(name);
        List<Book> bookList = new ArrayList<>(books);
        assertThat(bookList).hasSize(2);
        assertThat(bookList)
                .extracting("name")
                .containsAnyOf("The Great Gatsby","The Catcher in the Rye");
    }
    @ParameterizedTest
    @ValueSource(strings = {"tragedy", "Tragedy", "TRAGEDY"})
    public void should_find_search_by_category(String category) {
        Book book1 = new Book(1L, "The Great Gatsby","978-3-16-148410-0","F. Scott Fitzgerald","Tragedy");
        Book book2 = new Book(2L, "To Kill a Mockingbird", "978-0-446-31078-9", "Harper Lee", "Fiction");
        Book book3 = new Book(3L, "Pride and Prejudice", "978-0-14-143951-8", "Jane Austen", "Tragedy");
        Book book4 = new Book(4L, "The Catcher in the Rye", "978-0-316-76948-8", "J.D. Salinger", "Coming of Age");
        List<Book> savedBooks =new ArrayList<>();
        savedBooks.add(book1);
        savedBooks.add(book2);
        savedBooks.add(book3);
        savedBooks.add(book4);


        bookRepository.saveAll(savedBooks);
        Set<Book> books = bookRepository.findByCategorieIgnoreCaseContaining(category);
        List<Book> bookList = new ArrayList<>(books);
        assertThat(bookList).hasSize(2);
        assertThat(bookList)
                .extracting("name")
                .containsAnyOf("The Great Gatsby","Pride and Prejudice");
    }

}