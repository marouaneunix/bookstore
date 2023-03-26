package ma.norsys.bookstore.book;


import ma.norsys.bookstore.BookstoreApplication;
import ma.norsys.bookstore.Entities.Book;
import ma.norsys.bookstore.Repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = BookstoreApplication.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
    }
    @Test
    public void should_return_books() throws Exception  {

        // arrange
        Book book = new Book();
        book.setAuthor("mouad");
        book.setCategory("comedy horror");
        book.setDescription("the best book ever");
        book.setIsbn("123-14");
        book.setName("java");
        bookRepository.save(book);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("http://localhost:8090/books")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("java"))
                .andExpect(jsonPath("$[0].author").value("mouad"))
                .andExpect(jsonPath("$[0].category").value("comedy horror"))
                .andExpect(jsonPath("$[0].description").value("the best book ever"))
                .andExpect(jsonPath("$[0].isbn").value("123-14")) ;











    }
}
