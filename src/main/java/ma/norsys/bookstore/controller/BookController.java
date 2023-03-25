package ma.norsys.bookstore.controller;


import ma.norsys.bookstore.entity.Book;
import ma.norsys.bookstore.exception.BookNotFoundException;
import ma.norsys.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    public ResponseEntity<Book> getBookById(@PathVariable long id) {
        try {
            Book book = bookService.getBookById(id);
            return ResponseEntity.ok(book);
        } catch (BookNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("search/{search}")
    public ResponseEntity<List<Book>> getBooksBySearchTerm(@PathVariable String search) {
        List<Book> books = bookService.findBooksBySearchTerm(search);
        return ResponseEntity.ok(books);
    }
    @GetMapping("/search")
    public ResponseEntity<List<Book>> getUserByIdAndName(@RequestParam(value = "name", required = false) String name,
                                                         @RequestParam(value = "categories", required = false) String category
    )
    {
        Set<Book> searchedBooks = new HashSet<>();
        if (name != null && category != null) {
            for (String cat : category.split(",")) {
                searchedBooks.addAll(bookService.findBooksByTitleAndCategory(name, cat));
            }

        } else if (name != null) {
            List<Book> bookList=bookService.findBooksBySearchTerm(name);
            return ResponseEntity.ok(bookList);
        } else if (category != null) {
            for (String cat : category.split(",")) {
                searchedBooks.addAll(bookService.findBooksByCategory(cat));
            }
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
        List<Book> bookList = new ArrayList<>(searchedBooks);
        return ResponseEntity.ok(bookList);
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
