package ma.norsys.bookstore;

import ma.norsys.bookstore.models.Book;
import ma.norsys.bookstore.repositories.BookRepository;
import ma.norsys.bookstore.services.BookServiceImp;
import org.junit.jupiter.api.BeforeEach;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = BookstoreApplication.class)
public class BookControllerTest {

    @Autowired
    BookServiceImp bookService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
        List<Book> books = List.of(new Book(1, "Les Sept Habitudes des gens efficaces", "L1234", "Stephen Covey", "Les Sept Habitudes des Gens Efficaces (The Seven Habits of Highly Effective People), publié en 1989, est un ouvrage de développement personnel écrit par Stephen Covey. Vendu à plus de 15 millions d'exemplaires (2004) dans 38 " +
                        "langues, le livre énumère sept principes qui, si établis en tant qu'habitude", "science vie"),
                new Book(2, "Harry Potter and the Prisoner of Azkaban", "K45678", "J.K. Rowling", "Harry Potter and the Prisoner of Azkaban is a 2004 fantasy film directed by Alfonso Cuarón from a screenplay by Steve Kloves, based on the 1999 novel of the same name by J. K. Rowling. It is the sequel to Harry Potter and the Chamber of Secrets (2002) and the third instalment in the Harry Potter film series. The film stars Daniel Radcliffe as Harry Potter, alongside Rupert Grint and Emma Watson as Harry's" +
                        " best friends Ron Weasley and Hermione Granger respectively.", "dcience"),
                new Book(3, "L'Alchimiste", "L56734", "J.K. Rowling", "Paulo Coelho a présenté son roman comme une illustration des « quatre clés fondamentales de l'alchimie »1, que sont selon lui « les Signes »2, « l'Âme du Monde »3, « la Légende personnelle »4 et « le Langage du Cœur »5.\n" +
                        "\n", "Roman Drama"),
                new Book(4, "Spring Boot", "K985", "J.K. Rowling", "Java Spring Boot (Spring Boot) is a tool that makes developing web application and microservices with Spring Framework faster and easier through three core capabilities: Autoconfiguration. An opinionated approach to configuration. The ability to create standalone applications.", "drama Fantasy"),
                new Book(5, "Harry Potter and the Chamber of Secrets", "LJ764", "JK Rowling", "Harry Potter et la Chambre des secrets est le deuxième roman de la série littéraire centrée sur le personnage de Harry Potter créé par J. K. Rowling. Il a été publié le 2 juillet 1998 par Bloomsbury et le 23 mars 1999 en France."
                        , "Roman, Fantasy littéraire")

        );

        books.forEach((book) -> {
            bookRepository.save(book);
        });
    }


    @Test
    public void should_return_books() throws Exception {

        mockMvc.perform(get("/books")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Les Sept Habitudes des gens efficaces"))
                .andExpect(jsonPath("$[0].isbn").value("L1234"))
                .andExpect(jsonPath("$[1].isbn").value("K45678"))
                .andExpect(jsonPath("$[2].isbn").value("L56734"))
                .andExpect(jsonPath("$.length()").value(5));
    }

    @Test
    public void should_find_search_by_TitleOnly() throws Exception {

        mockMvc.perform(get("/books/filter")
                        .param("title", "potter")
                        .param("categories", "")
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].isbn").value("LJ764"))
                .andExpect(jsonPath("$[1].isbn").value("K45678"))
                .andExpect(jsonPath("$.length()").value(2));

    }

    @Test
    public void should_find_search_by_CategoryOnly() throws Exception {
        mockMvc.perform(get("/books/filter")
                        .param("title", "")
                        .param("categories", "science")
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].isbn").value("L1234"))
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    public void should_find_search_by_CategoryAndTitle() throws Exception {
        mockMvc.perform(get("/books/filter")
                        .param("title", "miste")
                        .param("categories", "roman")
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].isbn").value("L56734"))
                .andExpect(jsonPath("$.length()").value(1));
    }


}
