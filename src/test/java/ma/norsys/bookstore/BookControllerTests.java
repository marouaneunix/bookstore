package ma.norsys.bookstore;

import ma.norsys.bookstore.models.Book;
import ma.norsys.bookstore.repositories.BookRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = BookstoreApplication.class)
class BookControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
    }
    @Test
    public void shouldGetAllBooks() throws Exception  {


        Book book = new Book (0,"L'Alchimiste","dkcyd","Paulo Coelho","L'Alchimiste est un conte philosophique de Paulo Coelho paru en 1988.","Classics");

        bookRepository.save(book);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/books")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("L'Alchimiste"));
    }



    @Test
    public void getBooksByCategories() throws Exception {
        Book book1 = new Book (0,"L'Alchimiste","dkcyd","Paulo Coelho","L'Alchimiste est un conte philosophique de Paulo Coelho paru en 1988.","Classics");
        Book book2 = new Book (0,"Harry Potter and the Chamber of Secrets","dkjjjjcyd","J.K. Rowling","The plot follows Harry's second year at Hogwarts School of Witchcraft and Wizardry, ","Fantasy");
        Book book3 = new Book (0,"Harry Potter and the Prisoner of Azkaban","djjjcyd","J.K. Rowling","The plot follows Harry's second year at Hogwarts School of Witchcraft and Wizardry, ","Adventure stories");

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/titlesAndCategories", "Harry Potter")
                        .param("categories", "Fantasy")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Harry Potter and the Chamber of Secrets"));
    }

    @Test
    public void getBooksByTitle() throws Exception {
        Book book1 = new Book (0,"L'Alchimiste","dkcyd","Paulo Coelho","L'Alchimiste est un conte philosophique de Paulo Coelho paru en 1988.","Classics");
        Book book2 = new Book (0,"Harry Potter and the Chamber of Secrets","dkjjjjcyd","J.K. Rowling","The plot follows Harry's second year at Hogwarts School of Witchcraft and Wizardry, ","Fantasy");
        Book book3 = new Book (0,"Harry Potter and the Prisoner of Azkaban","djjjcyd","J.K. Rowling","The plot follows Harry's second year at Hogwarts School of Witchcraft and Wizardry, ","Adventure stories");

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/titlesAndCategories", "Harry Potter")
                        .param("title", "L'Alchimiste")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].isbn").value("dkcyd"));
    }

    @Test
    public void getBooksByTitleAndCategories() throws Exception {
        Book book1 = new Book (0,"L'Alchimiste","dkcyd","Paulo Coelho","L'Alchimiste est un conte philosophique de Paulo Coelho paru en 1988.","Classics");
        Book book2 = new Book (0,"Harry Potter and the Chamber of Secrets","dkjjjjcyd","J.K. Rowling","The plot follows Harry's second year at Hogwarts School of Witchcraft and Wizardry, ","Fantasy");
        Book book3 = new Book (0,"Harry Potter and the Prisoner of Azkaban","djjjcyd","J.K. Rowling","The plot follows Harry's second year at Hogwarts School of Witchcraft and Wizardry, ","Adventure stories");

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/titlesAndCategories", "Harry Potter")
                        .param("title", "Secrets")
                        .param("categories", "Fantasy")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Harry Potter and the Chamber of Secrets"));
    }
}

