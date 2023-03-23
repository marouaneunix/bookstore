package ma.norsys.bookstore.book;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/books")
class BookController {

    private final BookService bookService;

    BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public ResponseEntity<List<BookDTO>> getBooks() {
        List<BookDTO> books = bookService.findAll().stream()
                .map(BookDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(books);
    }
}
