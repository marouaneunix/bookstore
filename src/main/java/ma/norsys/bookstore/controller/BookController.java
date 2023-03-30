package ma.norsys.bookstore.controller;


import ma.norsys.bookstore.entity.Book;
import ma.norsys.bookstore.exception.BookNotFoundException;
import ma.norsys.bookstore.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("books/")
@CrossOrigin("*")
public class BookController {

    private final BookService bookService;
    public BookController(BookService bookService){
        this.bookService=bookService;
    }

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

    @GetMapping("searchbytitle")
    public ResponseEntity<List<Book>> getBooksBySearchTerm(@RequestParam(value = "title", required = false) String title) {
        List<Book> books = bookService.findBooksBySearchTerm(title);
        return ResponseEntity.ok(books);
    }
    @GetMapping("searchbyCategory")
    public ResponseEntity<List<Book>> getBooksByCategory(@RequestParam(value = "category", required = false) String category) {
        List<Book> books = bookService.findBooksByCategory(category);
        return ResponseEntity.ok(books);
    }
    @GetMapping("searchbyAuthor")
    public ResponseEntity<List<Book>> getBooksByAuthor(@RequestParam(value = "author", required = false) String author) {
        List<Book> books = bookService.findBooksByAuthor(author);
        return ResponseEntity.ok(books);
    }
    @GetMapping("/search/title/category")
    public ResponseEntity<List<Book>> getBooksByTitleAndCategory(@RequestParam(value = "title", required = false) String name,
                                                         @RequestParam(value = "category", required = false) String category
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
        System.out.print(book.getTitle());
        return bookService.addBook(book);
    }

    @PutMapping("{id}")
    public Book UpdateUser(@RequestBody Book book){
        return bookService.updateBook(book);
    }
    @GetMapping("searchbyCatAndTilte")
    public ResponseEntity<List<Book>> searchBooksByTitleAndCategory(@RequestParam(value = "title", required = false) String name,
                                                                 @RequestParam(value = "category", required = false) String category
    ){
        List<Book> books = bookService.findBooksByTitleAndCategory(name,category);
        return ResponseEntity.ok(books);
    }

    @GetMapping("searchByTitleAndAuthor")
    public ResponseEntity<List<Book>> searchBooksByTitleAndAuthor(@RequestParam(value = "title", required = false) String name,
                                                                    @RequestParam(value = "author", required = false) String author
    ){
        List<Book> books = bookService.searchByTitleAndAuthor(name,author);
        return ResponseEntity.ok(books);
    }

    @GetMapping("searchByAuthorAndCategory")
    public ResponseEntity<List<Book>> searchBooksByAuthorAndCategory(@RequestParam(value = "author", required = false) String author,
                                                                  @RequestParam(value = "category", required = false) String category
    ){
        List<Book> books = bookService.searchByAuthorAndCategory(author,category);
        return ResponseEntity.ok(books);
    }



}
