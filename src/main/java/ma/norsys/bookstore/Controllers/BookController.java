package ma.norsys.bookstore.Controllers;

import ma.norsys.bookstore.Entities.Book;
import ma.norsys.bookstore.Services.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("books")
@CrossOrigin
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    @GetMapping()
    public List<Book> getAllbooks(){

        return bookService.getAllBooks();    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }


    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id){
        bookService.deleteBookById(id);
    }


    @GetMapping("/search/name/{name}")
    public List<Book> searchBookByName(@PathVariable String name){
         return bookService.searchByName(name);
    }

    @GetMapping("/search/categories/{categories}")

    public HashSet<Book> searchBookByCategory(@PathVariable String categories){

        return bookService.searchByCategory(categories);
    }



    @PostMapping()
    public void addBook( @RequestBody Book book){

        bookService.saveBook(book);
    }

}
