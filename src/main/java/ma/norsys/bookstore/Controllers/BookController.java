package ma.norsys.bookstore.Controllers;

import ma.norsys.bookstore.BookDTO;
import ma.norsys.bookstore.Exceptions.BookNotFoundException;
import ma.norsys.bookstore.Models.Book;
import ma.norsys.bookstore.Services.Impl.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@CrossOrigin
public
class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/title/{title}")
    public List<Book> findBookByTitle(@PathVariable String title){
        return  bookService.findBookByTitle(title);
    }
    @GetMapping("/categorie/{categorie}")
    public HashSet<Book> getAllBookByCategoryContaining(@PathVariable String categorie) {
        return bookService.getAllBookByCategoryContaining(categorie);
    }


    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id) throws BookNotFoundException {
        bookService.deleteBook(id);
    }












}
