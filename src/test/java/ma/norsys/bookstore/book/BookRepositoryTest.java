package ma.norsys.bookstore.book;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Set;

import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {
    @Autowired BookRepository bookRepository;

    @ParameterizedTest
    @ValueSource(strings = {"Java", "java", "JAVA"})
    public void should_find_search_by_criteria(String name) {


        List<Book> savedBooks = of(aBook("Effective Java", of("Tech")), aBook("Java 17", of()), aBook("Spring Boot", of("Tech")));
        bookRepository.saveAll(savedBooks);

        List<Book> books = bookRepository.searchByCriteria(name, List.of("Tech", "Front"));

        assertThat(books).hasSize(1);
        assertThat(books)
                .extracting("name")
                .containsAll(of("Effective Java"));

    }

    Book aBook(String name, List<String> categories) {
        Book book = new Book();
        book.setName(name);
        book.setCategories(categories);
        return book;
    }
}
