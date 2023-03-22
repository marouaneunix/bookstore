package ma.norsys.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ma.norsys.bookstore.entity.Book;
import ma.norsys.bookstore.service.BookServiceImpl;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    @GetMapping
    public List<Book> getBooks() {
        return bookService.getAllBook();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @GetMapping("/{id}")
    public Book getBookbyId(@PathVariable("id") int id) {
        return bookService.getBookById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteBookbyId(@PathVariable("id") int id) {
        bookService.deleteBook(id);
        return "Book deleted";
    }

    @GetMapping("/name/{name}")
    public Book getBookByName(@PathVariable("name") String name) {
        return bookService.getBookByName(name);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Book>> getBookByCategory(@PathVariable("category") String category) {
        return new ResponseEntity<List<Book>>(bookService.getBookByCategory(category), HttpStatus.OK);
    }
}
