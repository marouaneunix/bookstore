package ma.norsys.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

}


/*
@RestController
@RequestMapping("/api/v1/books")
class BookController {


	@GetMapping()
	public ResponseEntity<List<Book>> getBooks() {
		return ResponseEntity.ok(List.of(
				new Book(10L, "Effective Java"),
				new Book(10L, "Spring"),
		new Book(10L, "TDD")));
	}
}

record Book(Long id, String name){}*/
