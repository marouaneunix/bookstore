package ma.norsys.bookstore.controller;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getBooks() {
        List<Book> books = bookService.getAllBook();
        return ResponseEntity.ok().body(books);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        Book saveBook = bookService.saveBook(book);
        return ResponseEntity.ok().body(saveBook);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Book> getBookbyId(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(bookService.getBookById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookbyId(@PathVariable("id") int id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public Set<Book> searchBooks(@RequestParam(value = "name") String name,
            @RequestParam(value = "category") String category) {
        Set<Book> searchBook = new HashSet<>();
        if (name != null && category != null) {
            for (String cat : category.split(",")) {
                searchBook.addAll(bookService.getBookByNameAndCategory(name, cat));
            }
            return searchBook;
        } else if (name != null) {
            searchBook.addAll(bookService.getBookByName(name));
            return searchBook;
        } else if (category != null) {
            for (String cat : category.split(",")) {
                searchBook.addAll(bookService.getBookByCategory(cat));
            }
            return searchBook;
        }
        return Collections.EMPTY_SET;

    }
}
