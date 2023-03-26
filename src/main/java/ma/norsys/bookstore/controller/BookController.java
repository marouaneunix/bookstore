package ma.norsys.bookstore.controller;

import ma.norsys.bookstore.entity.Book;
import ma.norsys.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getAllBook();
    }


    @GetMapping("/{id}")
    public Book getBookById(@PathVariable("id") int id) throws Exception {
        return bookService.getBook(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@RequestBody Book book)
    {
        return bookService.createBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") int id)
    {
        bookService.deleteBook(id);
    }

    @GetMapping("/search")
    public Set<Book> searchBook(@RequestParam(value = "name") String name, @RequestParam(value = "categories") String categories)
    {
        Set<Book> searchedBooks=new HashSet<>();
        if(name!=null && categories!=null)
        {
            for(String category: categories.split(","))
            {
                searchedBooks.addAll(bookService.searchByNameByCategory(name,category));
            }
            return searchedBooks;
        }
        else if(name!=null){
            searchedBooks.addAll(bookService.searchByName(name));
            return searchedBooks;
        }
        else if(categories!=null)
        {
            for(String category: categories.split(","))
            {
                searchedBooks.addAll(bookService.searchByCategory(category));
            }
            return searchedBooks;
        }
        return Collections.EMPTY_SET;
    }
}
