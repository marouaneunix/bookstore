package ma.norsys.bookstore;

import ma.norsys.bookstore.book.Book;
import ma.norsys.bookstore.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class BookstoreApplication implements CommandLineRunner {

	@Autowired
	BookRepository bookRepository;
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);

	}

	@Override
	public void run(String... args)  {
		Book book  = new Book("Effective Java", List.of("Tech", "back"));
		Book book1  = new Book("Spring Book", List.of("Tech"));
		Book book2 = new Book("Docker", List.of("devops"));
		Book book3  = new Book("React", List.of("front"));
		bookRepository.deleteAll();
		bookRepository.saveAll(List.of(book, book2, book1, book3));
	}
}


