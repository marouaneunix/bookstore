package ma.norsys.bookstore;

import ma.norsys.bookstore.Services.LivreService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;


@SpringBootApplication
public class BookstoreApplication implements CommandLineRunner{
	@Autowired
	LivreService livreService;

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}


