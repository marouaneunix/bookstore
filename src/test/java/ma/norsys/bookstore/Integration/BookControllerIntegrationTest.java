package ma.norsys.bookstore.Integration;

import ma.norsys.bookstore.BookstoreApplication;
import ma.norsys.bookstore.entity.Book;
import ma.norsys.bookstore.repository.BookRepository;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = BookstoreApplication.class)
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
        Book book1 = new Book(1L,"Fundamentals of Wavelets","MG395AD","mohamed","2003","Horror","dec");
        Book book2 = new Book(2L,"rich dad por dad","TC515KL","samir","2003","crime","dec");
        bookRepository.save(book1);
        bookRepository.save(book2);
    }
    @Test
    public void should_return_books() throws Exception  {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/books/")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Fundamentals of Wavelets"))
                .andExpect(jsonPath("$[0].author").value("mohamed"))
                .andExpect(jsonPath("$[0].isbn").value("MG395AD"));
    }

    @Test
    public void should_search_for_books() throws Exception{
        mockMvc.perform(get("/books/search?name=fundamentals&categories=Horror")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Fundamentals of Wavelets"))
                .andExpect(jsonPath("$[0].author").value("samir"))
                .andExpect(jsonPath("$[0].isbn").value("MG395AD"));

    }
}
