package ma.norsys.bookstore.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import ma.norsys.bookstore.controller.BookController;
import ma.norsys.bookstore.entity.Book;
import ma.norsys.bookstore.exception.BookNotFoundException;
import ma.norsys.bookstore.service.BookService;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@WebMvcTest(BookController.class)
class BookControllerTest {

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
        Book book1 = new Book(1L,"Fundamentals of Wavelets","Goswami","samir","2003","Category","dec");
        Book book2 = new Book(2L,"Fundamentals of Wavelets","Goswami","samir","2003","Category","dec");
        books = Arrays.asList(book1,book2);
    }



    @Test
    public void should_find_all_books() throws Exception {

        when(bookService.getAllBooks()).thenReturn(books);

        mockMvc.perform(get("/books/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Fundamentals of Wavelets"))
                .andExpect(jsonPath("$[0].author").value("samir"))
                .andExpect(jsonPath("$[0].yearofpub").value("2003"))
                .andExpect(jsonPath("$[1].title").value("Fundamentals of Wavelets"))
                .andExpect(jsonPath("$[1].author").value("samir"))
                .andExpect(jsonPath("$[1].yearofpub").value("2003"));

        verify(bookService).getAllBooks();
    }

    @Test
    public void should_get_book_by_id() throws Exception {
        when(bookService.getBookById(1L)).thenReturn(books.get(0));

        mockMvc.perform(get("/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Fundamentals of Wavelets"))
                .andExpect(jsonPath("$.author").value("samir"))
                .andExpect(jsonPath("$.yearofpub").value("2003"));

        verify(bookService).getBookById(1L);
    }


    @Test
    public void should_delete_book_by_id() throws Exception{
        doNothing().when(bookService).deleteBookById(1L);
        mockMvc.perform( MockMvcRequestBuilders.delete("/books/1"))
                .andExpect(status().isOk());
        verify(bookService).deleteBookById(1L);
    }

    @Test
    public void should_update_book() throws Exception{
        Book newBook = new Book(1L,"Fundamentals of Wavelets","Goswami","samir","2003","Category","dec");


        when( bookService.updateBook(newBook)).thenReturn(newBook);

        mockMvc.perform(put("/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(newBook)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Fundamentals of Wavelets"))
                .andExpect(jsonPath("$.author").value("samir"));

        verify(bookService).updateBook(newBook);

    }


    @Test
    public void should_add_book() throws Exception{
        Book newBook = new Book(4L,"Fundamentals of Wavelets","Goswami","samir","2003","Category","dec");

        when(bookService.addBook(newBook)).thenReturn(newBook);
        mockMvc.perform(post("/books/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(newBook)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Fundamentals of Wavelets"))
                .andExpect(jsonPath("$.author").value("samir"));

        verify(bookService).addBook(newBook);
    }

    @Test
    public void should_search_book_by_category_and_name() throws Exception{
        List<Book> bookList=new ArrayList<>();
        Book newBook = new Book(4L,"Fundamentals of Wavelets","Goswami","samir","2003","Horror","dec");
        bookList.add(newBook);
        when(bookService.findBooksByTitleAndCategory("fundamentals","Horror")).thenReturn(bookList);
        mockMvc.perform(get("/books/search?name=fundamentals&categories=Horror")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Fundamentals of Wavelets"))
                .andExpect(jsonPath("$[0].author").value("samir"))
                .andExpect(jsonPath("$[0].yearofpub").value("2003"));


        verify(bookService).findBooksByTitleAndCategory("fundamentals","Horror");

    }

    @Test
    public void should_return_404_when_user_not_found() throws Exception {
        long id = 44L;
        when(bookService.getBookById(id)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found with id: " + id));

        mockMvc.perform(get("/books/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        verify(bookService).getBookById(id);
    }
    @Test
    public void givenNonexistentUserId_whenGetByIt_thenThrowsUserNotFoundException() throws Exception {
        long id = 44L;
        when(bookService.getBookById(id)).thenThrow(new BookNotFoundException(id));

        mockMvc.perform(get("/books/{id}", 44)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value("Book with id: "+ id +" not found"));

        verify(bookService).getBookById(id);
    }

}
