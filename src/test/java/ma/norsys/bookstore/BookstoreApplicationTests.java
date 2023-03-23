package ma.norsys.bookstore;

import com.fasterxml.jackson.databind.ObjectMapper;
import ma.norsys.bookstore.Controllers.BookController;
import ma.norsys.bookstore.Models.Book;
import ma.norsys.bookstore.Services.Impl.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class BookstoreApplicationTests {

	@Test
	void contextLoads() {
	}

	@MockBean
	BookService bookService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetAllBooks() throws Exception {

		List<Book> usersList= Arrays.asList(
				Book.builder().title("livre1").isbn("fd123").auteur("mouad").build(),
				Book.builder().title("livre2").isbn("hd1456").auteur("mohamed").build());

		when(bookService.getAllBooks()).thenReturn(usersList);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/books")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("livre1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].isbn").value("fd123"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("livre2"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].isbn").value("hd1456"));

		verify(bookService,times(1)).getAllBooks();

	}


	@Test
	public void testAddBook()throws Exception{
		Book book= Book.builder().title("livre1").isbn("2010").build();
		when(bookService.addBook(book)).thenReturn(book);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/books")
						.content(new ObjectMapper().writeValueAsString(book))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.title").value("livre1"));

		verify(bookService,times(1)).addBook(book);
	}

	@Test
	public void testDeleteBook() throws Exception {

		doNothing().when(bookService).deleteBook(1);

		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/books/1")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		verify(bookService,times(1)).deleteBook(1);

	}







}
