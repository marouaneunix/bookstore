package ma.norsys.bookstore.controller;

import ma.norsys.bookstore.models.Book;
import ma.norsys.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @PostMapping("/list")
    public List<Book> getBooksByNameAndCategory(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "category", required = false) String category
    ) {
        return bookService.findByNameAndCategory(name, category);
    }

    @GetMapping("/{bookId}")
    public Optional<Book> getBook(@PathVariable int bookId) {
        return bookService.findById(bookId);
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.createProduct(book);
    }


    @DeleteMapping("/{bookId}")
    public int deleteBook(@PathVariable int bookId) {
        Optional<Book> book = bookService.findById(bookId);

        if (!book.isPresent()) {
            throw new RuntimeException("Book with ID: " + bookId + " not found.");
        }

        bookService.deleteBookById(bookId);

        return bookId;
    }


}




