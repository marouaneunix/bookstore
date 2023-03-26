package ma.norsys.bookstore;

import ma.norsys.bookstore.Controller.LivreController;
import ma.norsys.bookstore.Entity.Livre;
import ma.norsys.bookstore.Services.LivreService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class BookstoreApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	LivreController livreController;
	@MockBean
	LivreService livreService;
	@Test
	public void contextLoads() throws Exception{
	}
	@Test
	public void testLivresList() throws Exception {
		Livre livre1 = new Livre(1,"JAVA","Jonny",
				"a book about java","gtdj","nnnvb");
		Livre livre2 = new Livre(2,"Spring","John smith",
				"a book about spring development ","J143Rt","nfnv");
		List<Livre> livresList = Arrays.asList(livre1, livre2);
		Mockito.when(livreService.getAllLivres()).thenReturn(livresList);
		mockMvc.perform(get("/api/v1/books/"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].name").value("JAVA"));
		verify(livreService, times(1)).getAllLivres();

	}
	@Test
	public void testGetLivre() throws Exception {
		Livre livre = new Livre(1,"JAVA","Jonny",
				"a book about java","gtdj","nnnvb");
		Mockito.when(livreService.getLivreById(1)).thenReturn(livre);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/books/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name").value("JAVA"))
				.andExpect(jsonPath("$.auteur").value("Jonny"));
		verify(livreService, times(1)).getLivreById(1);

	}
	@Test
	public void testSaveLivre() throws Exception {
		Livre livre = new Livre(1,"JAVA","Jonny",
				"a book about java","gtdj","nnnvb");
		Mockito.when(livreService.createLivre(Mockito.any(Livre.class))).thenReturn(livre);
		mockMvc.perform(post("/api/v1/books/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(livre)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect( jsonPath("$.auteur").value("Jonny"))
				.andExpect( jsonPath("$.isbn").value("gtdj"));
		verify(livreService, times(1)).createLivre(livre);
	}

	@Test
	public void testUpdateLivre() throws Exception {
		Livre livre = new Livre(1,"JAVA","Jonny",
				"a book about java","gtdj","nnnvb");
		Mockito.when(livreService.updateLivre(Mockito.any(Livre.class))).thenReturn(livre);
		mockMvc.perform(put("/api/v1/books/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(livre)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name").value("JAVA"))
				.andExpect(jsonPath("$.auteur").value("Jonny"));
		verify(livreService, times(1)).updateLivre(livre);

	}
	@Test
	public void testDeleteLivre() throws Exception {
		mockMvc.perform(delete("/api/v1/books/1"))
				.andExpect(status().isOk());
	}
	@Test
	public void testGetLivreByName() throws Exception {
		Livre livre = new Livre(1,"JAVA","Jonny",
				"a book about java","gtdj","nnnvb");
		Mockito.when(livreService.getLivreByName("JAVA")).thenReturn(livre);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/books/name/JAVA"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name").value("JAVA"))
				.andExpect(jsonPath("$.auteur").value("Jonny"));
		verify(livreService, times(1)).getLivreByName("JAVA");

	}
	@Test
	public void testGetLivreByCategory() throws Exception {
		Livre livre = new Livre(1,"JAVA","Jonny",
				"History","gtdj","a book about java");
		Mockito.when(livreService.getLivreByCategory("History")).thenReturn(livre);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/books/category/History"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name").value("JAVA"))
				.andExpect(jsonPath("$.auteur").value("Jonny"));
		verify(livreService, times(1)).getLivreByCategory("History");

	}

}
