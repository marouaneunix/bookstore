
package ma.norsys.bookstore.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.validation.Valid;
import ma.norsys.bookstore.models.Book;
import ma.norsys.bookstore.services.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.List;
import java.util.Map;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    IBookService bookService;



    @GetMapping
    public ResponseEntity<List<Book>> getAll(){
        List<Book> books= bookService.getAll();
        if(books.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(books,HttpStatus.OK);
    }

    @PostMapping
    public void addBook(@RequestBody Book book) {
        bookService.addBook(book);
    }


    @GetMapping("/titlesAndCategories")
    public Set<Book> getByNameAndCategories(@RequestParam(value="title", required=false) String title,
                                @RequestParam(value="categories", required=false) String categories){
        return bookService.findByCategoriesAndTitle (categories, title);
    }

    @DeleteMapping("/{id}")
    public boolean deleteBook(@PathVariable int id){
        return  bookService.deleteBook(id);
    }


}
