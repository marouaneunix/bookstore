package ma.norsys.bookstore.book;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
class BookController {

    private final BookService bookService;
    private final BookCriteriaRepository bookCriteriaRepository;


    BookController(BookService bookService, BookCriteriaRepository bookCriteriaRepository) {
        this.bookService = bookService;
        this.bookCriteriaRepository = bookCriteriaRepository;
    }


    @GetMapping
    public ResponseEntity<List<BookDTO>> searchBookByCriteria(@RequestParam(required = false) String name, @RequestParam(required = false) List<String> categories) {
        List<Book> books;
        if (name == null && (categories == null || categories.isEmpty())) {
            books = bookService.findAll();
        } else if (categories == null || categories.isEmpty()) {
            books = bookService.searchByName(name);
        } else if (name == null || name.isEmpty()) {
            books = bookService.searchByCategories(categories);
        } else {
            books = bookService.searchByNameAndCategories(name, categories);
        }

        return ResponseEntity.ok(books.stream()
                .map(BookDTO::new)
                .toList());
    }

    @GetMapping("/v2")
    public ResponseEntity<List<BookDTO>> searchBookByCriteriad(@RequestParam(required = false) String name, @RequestParam(required = false) List<String> categories) {
        List<BookDTO> books = bookCriteriaRepository.search(name, categories).stream()
                .map(BookDTO::new)
                .toList();
        return ResponseEntity.ok(books);
    }
}
