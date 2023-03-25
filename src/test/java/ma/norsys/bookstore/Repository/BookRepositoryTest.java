package ma.norsys.bookstore.Repository;


import ma.norsys.bookstore.Models.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {


    @Autowired
    BookRepository bookRepository;

    @Test
    public void should_find_search_by_criteria() {

        bookRepository.deleteAll();
        Book book1 = Book.builder()
                .title("livre1")
                .auteur("mouad")
                .isbn("1")
                .categories("fantasy young adult")
                .description("desc1")
                .build();
        bookRepository.save(book1);

        Book book2 = Book.builder()
                .title("livre2")
                .auteur("Harper Lee")
                .isbn("2")
                .categories("classic historical fiction")
                .description("desc2")
                .build();
        bookRepository.save(book2);

        Book book3 = Book.builder()
                .description("desc4")
                .title("1984")
                .auteur("auteur4")
                .isbn("3")
                .categories("classic dystopian")
                .build();
        bookRepository.save(book3);


        List<Book> books = bookRepository.getAllBookByCategoryContaining("classic");

        // Assert that the correct books are returned
        assertThat(books).hasSize(2);
        assertThat(books).containsOnly(book2, book3);

    }
}