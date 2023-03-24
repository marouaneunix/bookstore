package ma.norsys.bookstore;

import ma.norsys.bookstore.entities.Book;
import ma.norsys.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookstoreApplication implements CommandLineRunner {
    @Autowired
    BookService bookService;


    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Book book1=new Book(1L, "The Great Gatsby","978-3-16-148410-0","F. Scott Fitzgerald","Tragedy");
        Book book2 = new Book(2L, "To Kill a Mockingbird", "978-0-446-31078-9", "Harper Lee", "Fiction");
        Book book3 = new Book(3L, "1984", "978-0-14-103614-4", "George Orwell", "Science Fiction");
        Book book4 = new Book(4L, "Pride and Prejudice", "978-0-14-143951-8", "Jane Austen", "Romance");
        Book book5 = new Book(5L, "The Catcher in the Rye", "978-0-316-76948-8", "J.D. Salinger", "Coming of Age");
        Book book6 = new Book(6L, "The Lord of the Rings", "978-0-618-00221-0", "J.R.R. Tolkien", "Fantasy");
        bookService.createBook(book1);
        bookService.createBook(book2);
        bookService.createBook(book3);
        bookService.createBook(book4);
        bookService.createBook(book5);
        bookService.createBook(book6);
    }
}
