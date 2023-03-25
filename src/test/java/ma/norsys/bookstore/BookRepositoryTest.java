package ma.norsys.bookstore;

import ma.norsys.bookstore.entity.Book;
import ma.norsys.bookstore.repository.BookRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.List;

import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;

    @ParameterizedTest
    @ValueSource(strings = {"Wavelets", "wavelets", "WAVELETS"})
    public void should_find_search_by_criteria(String name) {

        List<Book> savedBooks = of(new Book(1L,"Fundamentals of Wavelets","Goswami","samir","2003","Category","dec")
                , new Book(2L,"walk of Wavelets","Goswami","samir","2003","Category","dec"));
        bookRepository.saveAll(savedBooks);

        List<Book> books = bookRepository.findBooksByTitleContainsIgnoreCase(name);

        assertThat(books).hasSize(2);
        assertThat(books)
                .extracting("title")
                .containsAll(of("Fundamentals of Wavelets", "walk of Wavelets"));

    }
}