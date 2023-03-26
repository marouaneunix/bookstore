package ma.norsys.bookstore.controllers;

import ma.norsys.bookstore.exceptions.PostArgumentNotValidException;
import ma.norsys.bookstore.models.Book;
import ma.norsys.bookstore.services.BookServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("books")
public class BookController {

    private final BookServiceImp bookService;

    BookController(BookServiceImp bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        Book Book = bookService.getById(id);

        return Book;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        List<Book> Books = bookService.getAll();
        if (Books.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(Books, HttpStatus.OK);
    }


    @PostMapping
    public Book addBook(@RequestBody Book book) throws PostArgumentNotValidException {
        return bookService.addBook(book);
    }


    @DeleteMapping("/{id}")
    public boolean deleteBook(@PathVariable int id) {
        return bookService.deleteBook(id);
    }


    @GetMapping("/filter")
    public Set<Book> getBooksByTitleOrCategories(@RequestParam(name = "title") String title,
                                                 @RequestParam(name = "categories") String categories) {

        return bookService.getByTitleOrCategoriesOrBoth(title, categories);
    }


}
