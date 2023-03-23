package ma.norsys.bookstore.controllers;


import jakarta.annotation.Nullable;
import ma.norsys.bookstore.exceptions.BookNotFoundException;
import ma.norsys.bookstore.exceptions.InvalidRequestException;
import ma.norsys.bookstore.models.Book;
import ma.norsys.bookstore.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("books")
public class BooksController {
    @Autowired
    private BooksService booksService;

    @GetMapping
    public ResponseEntity<List<Book>> getAll(@Nullable String keyword) {
        List<Book> books;
        if (keyword == null || keyword.isBlank()) {
            books = booksService.getAll();
        } else {
            System.out.println(keyword);
            books = booksService.search(keyword.trim());
        }
        return ResponseEntity.ok().body(books);
    }


    @PostMapping
    public ResponseEntity<?> save(@RequestBody Book book) throws InvalidRequestException {
        Book savedBook = booksService.save(book);
        return ResponseEntity.ok().body(savedBook);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> getById(@PathVariable Long bookId) throws BookNotFoundException {
        Book book = booksService.getById(bookId);
        return ResponseEntity.ok().body(book);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<?> update(@PathVariable Long bookId, @RequestBody Book book) throws BookNotFoundException, InvalidRequestException {
        Book existingBook = booksService.getById(bookId);
        existingBook.setIsbn(book.getIsbn());
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        Book savedBook = booksService.update(existingBook);
        return ResponseEntity.ok().body(savedBook);
    }


    @DeleteMapping("/{bookId}")
    public ResponseEntity<?> delete(@PathVariable Long bookId) throws BookNotFoundException {
        Boolean res = booksService.delete(bookId);
        return ResponseEntity.ok().body(res);
    }

}