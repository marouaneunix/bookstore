package ma.norsys.bookstore;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import ma.norsys.bookstore.models.Book;
import ma.norsys.bookstore.repositories.BooksRepository;
import ma.norsys.bookstore.services.BooksService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        mockMvc.perform(get("/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(booksJson));

        verify(bookService, times(1)).getAll();

    }

    @Test
    public void testSearch() throws Exception {
        
        String keyword = "th";
        
        List<Book> books=  booksRepository.findByTitleContainingIgnoreCase(keyword);
        books.addAll(booksRepository.findByCategoriesNameContainingIgnoreCase(keyword));
        books.addAll(booksRepository.findByAuthorContainingIgnoreCase(keyword));


        ObjectMapper objectMapper = new ObjectMapper();
        String booksJson = objectMapper.writeValueAsString(books);
        mockMvc.perform(get("/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(booksJson));

        verify(bookService, times(1)).getAll();

    }
}
