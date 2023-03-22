package ma.norsys.bookstore;

import com.fasterxml.jackson.databind.ObjectMapper;
import ma.norsys.bookstore.controller.BookController;
import ma.norsys.bookstore.entity.Book;
import ma.norsys.bookstore.exception.BookNotFoundException;
import ma.norsys.bookstore.service.BookService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@WebMvcTest(BookController.class)
class TpRestApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;
    @Test
    void contextLoad(){

    }


    List<Book> books;
    @BeforeEach
    void setup() {
        Book book1 = new Book(1L,"Fundamentals of Wavelets","Goswami, Jaideva","2003");
        Book book2 = new Book(2L,"Data Smart","Foreman, John","2008");
        books = Arrays.asList(book1,book2);
    }



    @Test
    public void testGetAllUser() throws Exception {

        when(bookService.getAllBooks()).thenReturn(books);

        mockMvc.perform(get("/books/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Fundamentals of Wavelets"))
                .andExpect(jsonPath("$[0].author").value("Goswami, Jaideva"))
                .andExpect(jsonPath("$[0].year").value("2003"))
                .andExpect(jsonPath("$[1].title").value("Data Smart"))
                .andExpect(jsonPath("$[1].author").value("Foreman, John"))
                .andExpect(jsonPath("$[1].year").value("2008"));

        verify(bookService,times(1)).getAllBooks();
    }

    @Test
    public void testGetUserById() throws Exception {
        when(bookService.getBookById(1L)).thenReturn(books.get(0));

        mockMvc.perform(get("/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Fundamentals of Wavelets"))
                .andExpect(jsonPath("$.author").value("Goswami, Jaideva"))
                .andExpect(jsonPath("$.year").value("2003"));
    }


    @Test
    public void testDeleteById() throws Exception{
        doNothing().when(bookService).deleteBookById(1L);
        mockMvc.perform( MockMvcRequestBuilders.delete("/books/2"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateUser() throws Exception{
        Book nouveauInfo = new Book(1L,"samir","samir","2003");

        when( bookService.updateBook(nouveauInfo)).thenReturn(nouveauInfo);
        Book utilisateurMiseAjour = bookService.updateBook(nouveauInfo);

        mockMvc.perform(put("/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(nouveauInfo)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("samir"))
                .andExpect(jsonPath("$.author").value("samir"));

        Assertions.assertEquals(utilisateurMiseAjour,nouveauInfo);


    }

    @Test
    public void testAddUser() throws Exception{
        Book nouveauInfo = new Book(4L,"samir","samir","samir@gmail.com");
        when(bookService.addBook(nouveauInfo)).thenReturn(nouveauInfo);
        mockMvc.perform(post("/books/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(nouveauInfo)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("samir"))
                .andExpect(jsonPath("$.author").value("samir"));
    }

    @Test
    public void should_return_404_when_user_not_found() throws Exception {
        long id = 44L;
        when(bookService.getBookById(id)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found with id: " + id));

        mockMvc.perform(get("/books/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    @Test
    public void givenNonexistentUserId_whenGetByIt_thenThrowsUserNotFoundException() throws Exception {
        long id = 44L;
        when(bookService.getBookById(id)).thenThrow(new BookNotFoundException(id));

        mockMvc.perform(get("/books/{id}", 44)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value("Book with id: "+ id +" not found"));

        verify(bookService, times(1)).getBookById(id);
    }

}
