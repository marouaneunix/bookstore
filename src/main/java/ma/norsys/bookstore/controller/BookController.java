package ma.norsys.bookstore.controller;
import ma.norsys.bookstore.exception.BookNotFoundException;
import ma.norsys.bookstore.model.Book;
import ma.norsys.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Book> books = bookService.getAll();
        return ResponseEntity.ok().body(books);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Book book)  {
        Book savedBook = bookService.save(book);
        return ResponseEntity.ok().body(savedBook);
    }



    @GetMapping("/titlesAndCategories")
    public ResponseEntity<?> findByCategoriesAndName(@RequestParam(value="name", required=false) String name,
                                @RequestParam(value="categories", required=false) String categories){
        Set<Book> books= bookService.findByCategoriesAndName(categories, name);
        return ResponseEntity.ok().body(books);
    }

    @GetMapping("/name")
    public ResponseEntity<?> findByName(@RequestParam(value="name") String name){
        Set<Book> books= bookService.findByName(name);
        return ResponseEntity.ok().body(books);
    }

    @GetMapping("/categories")
    public ResponseEntity<?> findByCategories(@RequestParam(value="categories") String categories){
        Set<Book> books= bookService.findByCategories(categories);
        return ResponseEntity.ok().body(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) throws BookNotFoundException {
        Optional<Book> book = bookService.getBookById(id);
        return ResponseEntity.ok().body(book);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws BookNotFoundException {
        Boolean response = bookService.delete(id);
        return ResponseEntity.ok().body(response);
    }

}
