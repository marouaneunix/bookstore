package ma.norsys.bookstore.controller;


import ma.norsys.bookstore.entity.Book;
import ma.norsys.bookstore.exception.BookNotFoundException;
import ma.norsys.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("books/")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping()
    public ResponseEntity<List<Book>> getAllUsers() {

        List<Book> books = bookService.getAllBooks();
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(books);
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> getUserById(@PathVariable long id) {
        try {
            Book book = bookService.getBookById(id);
            return ResponseEntity.ok(book);
        } catch (BookNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("{id}")
    public void deleteUserById(@PathVariable Long id){
        bookService.deleteBookById(id);
    }

    @PostMapping
    public Book addUser(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @PutMapping("{id}")
    public Book UpdateUser(@RequestBody Book book){
        return bookService.updateBook(book);
    }


}
