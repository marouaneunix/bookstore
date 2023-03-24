package ma.norsys.bookstore;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import ma.norsys.bookstore.models.Book;
import ma.norsys.bookstore.repositories.BooksRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = BookstoreApplication.class)
public class BooksControllerTest {

    @Autowired
    BooksRepository booksRepository;
    @Autowired
    private MockMvc mockMvc;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Transactional
    @DisplayName("Should retrieve all books when no search params is provided")
    public void shouldRetrieveAllBooksWhenNoSearchParamsIsProvided() throws Exception {
        List<Book> books = booksRepository.findAll();

        mockMvc.perform(get("/api/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(books)));

    }

    @Test
    @Transactional
    @DisplayName("Should retrieve books based on name when name param is provided")
    public void shouldRetrieveBooksBasedOnNameWhenNameParamIsProvided() throws Exception {
        String name = "Velit";
        String category = null;

        List<Book> books = booksRepository.findByTitleContainingIgnoreCase(name);
        mockMvc.perform(get("/api/books")
                        .param("name", name)
                        .param("category", category)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(books)));

    }

    @Test
    @Transactional
    @DisplayName("Should retrieve books based on category when category param is provided")
    public void shouldRetrieveBooksBasedOnCategoryWhenCategoryParamIsProvided() throws Exception {
        String name = null;
        String category = "Sience";

        List<Book> books = booksRepository.findByCategoriesNameContainingIgnoreCase(category);
        mockMvc.perform(get("/api/books")
                        .param("name", name)
                        .param("category", category)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(books)));

    }

    @Test
    @Transactional
    @DisplayName("Should retrieve books based on category and name when category param is provided")
    public void shouldRetrieveBooksBasedOnCategoryAndNameWhenCategoryParamIsProvided() throws Exception {
        String name = null;
        String category = "Sience";

        List<Book> books = booksRepository.findByCategoriesNameContainingIgnoreCase(category);

        mockMvc.perform(get("/api/books")
                        .param("name", name)
                        .param("category", category)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(books)));


    }
}
