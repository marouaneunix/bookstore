package ma.norsys.bookstore;


import com.fasterxml.jackson.databind.ObjectMapper;
import ma.norsys.bookstore.model.Book;
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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BookControllerTest {


    @Autowired
    BookRepository bookRepository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void should_return_books() throws Exception  {

        // arrange
        Book book = new Book(1,"Java","Maroua","12345687","computer_science","asdfghjkl");
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/books")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Java"));
    }

 /**   @Test
    public void givenNonexistentBook_whenSave_thenStatus200() throws Exception {

        //Book book = new Book(16, "book16");
        when(bookService.save(book)).thenReturn(book);


        mockMvc.perform(post("/books/saveBook")
                        .content(asJsonString(book))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(book));

        verify(bookService, times(1)).save(book);
    }

    @Test
    public void whenGetAll_thenStatus200() throws Exception {
        List<Book> books = List.of(
                new Book(1, "book1"),
                new Book(2, "book2")
        );
        when(bookService.getAll()).thenReturn(books);

        ObjectMapper objectMapper = new ObjectMapper();
        String booksJson = objectMapper.writeValueAsString(books);

        mockMvc.perform(get("/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(booksJson));

        verify(bookService, times(1)).getAll();
    }

    @Test
    public void givenExistentBookId_whenGetBookById_thenStatus200() throws Exception {

        Book book = new Book(1, "book1");

        when(bookService.getBookById(1)).thenReturn(book);

        mockMvc.perform(get("/books/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(book));

        verify(bookService, times(1)).getBookById(1);
    }

    @Test
    public void givenNonexistentBookId_whenGetBookById_thenThrowsBookNotFoundException() throws Exception {

        when(bookService.getBookById(18L)).thenThrow(new BookNotFoundException("Book not found"));

        mockMvc.perform(get("/books/{id}", 18)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.equalTo("Book not found")));

        verify(bookService, times(1)).getBookById(18L);
    }
    @Test
    public void givenExistentBook_whenDelete_thenStatus200() throws Exception {

        when(bookService.delete(14L)).thenReturn(true);

        mockMvc.perform(delete("/books/deleteBook/{id}", 14))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(true)));

        verify(bookService, times(1)).delete(14L);
    }
    @Test
    public void givenNonexistentBook_whenDelete_thenThrowsBookNotFoundException() throws Exception {
        when(bookService.delete(15L)).thenThrow(new BookNotFoundException("Book not found"));

        mockMvc.perform(delete("/books/deleteBook/{id}", 15L))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.equalTo("Book not found")));

        verify(bookService, times(1)).delete(15L);
    }*/
}
