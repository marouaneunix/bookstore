package ma.norsys.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
//@ComponentScan(basePackages = {"ma.norsys.bookStore.service"})
//@EnableJpaRepositories ("ma.norsys.bookStore.repository")
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

}

/**
@RestController
@RequestMapping("/api/v1/books")
class BookController {

	@GetMapping()
	public ResponseEntity<List<BookDTO>> getBooks() {
		return ResponseEntity.ok(List.of(
				new BookDTO(10L, "Effective Java"),
				new BookDTO(10L, "Spring"),
		        new BookDTO(10L, "TDD")
		)
		);
	}
}
*/
//record BookDTO(Long id, String name){}