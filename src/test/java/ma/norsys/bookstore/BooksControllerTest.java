package ma.norsys.bookstore;

import static org.mockito.Mockito.*;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import ma.norsys.bookstore.models.Book;
import ma.norsys.bookstore.repositories.BooksRepository;
import ma.norsys.bookstore.services.BooksService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = BookstoreApplication.class)
public class BooksControllerTest {

    @Autowired
    BooksRepository booksRepository;
    @MockBean
    BooksService bookService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAll() throws Exception {
        // Set up test data
        List<Book> books = booksRepository.findAll();

        ObjectMapper objectMapper = new ObjectMapper();
        String booksJson = objectMapper.writeValueAsString(books);
        mockMvc.perform(get("/api/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(booksJson));

        verify(bookService, times(1)).getAll();

    }

    @Test
    public void testSearch() throws Exception {

        String keyword = "th";

        List<Book> books = booksRepository.findByTitleContainingIgnoreCase(keyword);
        books.addAll(booksRepository.findByCategoriesNameContainingIgnoreCase(keyword));
        books.addAll(booksRepository.findByAuthorContainingIgnoreCase(keyword));

        ObjectMapper objectMapper = new ObjectMapper();
        String booksJson = objectMapper.writeValueAsString(books);
        mockMvc.perform(get("/api/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(booksJson));

        verify(bookService, times(1)).getAll();

    }
}
