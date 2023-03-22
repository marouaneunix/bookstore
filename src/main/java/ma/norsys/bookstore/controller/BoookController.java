package ma.norsys.bookstore.controller;

import ma.norsys.bookstore.dto.BookDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/books")
class BoookController {


    @GetMapping
    public ResponseEntity<List<BookDTO>> getBooks() {
        return ResponseEntity.ok(List.of(
                new BookDTO(10L, "Effective Java"),
                new BookDTO(10L, "Spring"),
                new BookDTO(10L, "TDD")));
    }}