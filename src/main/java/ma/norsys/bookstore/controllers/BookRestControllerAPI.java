package ma.norsys.bookstore.controllers;
import ma.norsys.bookstore.entities.Book;
import ma.norsys.bookstore.exception.BookNotFoundException;
import ma.norsys.bookstore.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("books")
public class BookRestControllerAPI {
    private final BookService bookService;

    public BookRestControllerAPI(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }
    @GetMapping(path = "/{idBook}")
    public Book getBookById(@PathVariable Long idBook) throws BookNotFoundException {
        return bookService.getBookById(idBook);
    }
    @PostMapping
    public Book saveBook(@RequestBody Book book){
        return bookService.createBook(book);
    }
    @PutMapping
    public Book updateBook(@RequestBody Book book){
        return bookService.updateBook(book);
    }

    @DeleteMapping(path = "/{idBook}")
    public void deleteBook(@PathVariable Long idBook){
        bookService.deleteBookById(idBook);
    }

    @GetMapping("/search")
    public Set<Book> searchBooks(
            @RequestParam(value="name", required=false) String name,
            @RequestParam(value="categories", required=false) String category) {
        Set<Book> searchedBooks = new HashSet<>();

        if (!name.equals("") && !category.equals("")) {
            // search by both name and category
            for (String cat : category.split(",")) {
                searchedBooks.addAll(bookService.getBooksByNameAndCategory(name, cat));
            }
            return searchedBooks;
        } else if (!name.equals("")) {
            // search by name only
            return bookService.getBooksByNameContain(name);
        } else if (!category.equals("")) {
            // search by category only
            for (String cat : category.split(",")) {
                searchedBooks.addAll(bookService.getBooksByCategoriesContain(cat));
            }
            return searchedBooks;
        } else {
            // no parameters provided
            return new HashSet<>(bookService.getAllBooks());
        }
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<?> BookNotFound(BookNotFoundException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
