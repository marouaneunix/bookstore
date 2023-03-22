package ma.norsys.bookstore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import ma.norsys.bookstore.entity.Book;
import ma.norsys.bookstore.repository.BookRepository;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.CoreMatchers.is;

@SpringBootTest
@AutoConfigureMockMvc
public class BookMysqlTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Book book;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
        book = new Book(1, "12-3908", "The_school_of_life", "Alain de Botton", "vie", "school life");
    }

    @Test
    @Order(1)
    void should_be_able_to_save_one_book() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/books")
                .content(objectMapper.writeValueAsString(book))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(book.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn", is(book.getISBN())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author", is(book.getAuthor())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(book.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category", is(book.getCategory())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", is(book.getDescription())));
    }

    @Test
    @Order(2)
    void should_be_able_to_retrieve_all_book() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/books")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }
}
