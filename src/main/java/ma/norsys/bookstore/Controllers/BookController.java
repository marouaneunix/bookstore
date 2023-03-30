package ma.norsys.bookstore.Controllers;

import ma.norsys.bookstore.Entities.Book;
import ma.norsys.bookstore.Services.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @GetMapping("/searchh")

    public List<Book> findBookByNameContainingIgnoreCase(@RequestParam(value = "name") String name){

        return bookService.searchByName1(name);
    }


        @PutMapping()
        public Book updateBook( @RequestBody Book book){

            return bookService.updateBook(book);
        }

    @PostMapping()
    public Book addBook( @RequestBody Book book){

         return bookService.saveBook(book);
    }

    @GetMapping("/search")
    public Set<Book> searchBook(@RequestParam(value = "name") String name,@RequestParam(value = "author") String author, @RequestParam(value = "categories") String categories)
    {
        Set<Book> searchedBooks=new HashSet<>();
        if(name!="" && categories!="" && author!="" )
        {
            System.out.println("koulchi machi null");
            searchedBooks.addAll(bookService.searchByName1(name));
            searchedBooks.addAll(bookService.searchByAuthor(author));
            for(String category: categories.split(" "))
            {
                searchedBooks.addAll(bookService.searchByCategory1(category));
            }
            return searchedBooks;
        }
        else if(name!="" && author!=""){

            System.out.println("name author");
            searchedBooks.addAll(bookService.searchByAuthor(author));
            searchedBooks.addAll(bookService.searchByName1(name));
            return searchedBooks;
        }

        else if(author!="" && categories!=""){

            System.out.println("categori author");
            searchedBooks.addAll(bookService.searchByAuthor(author));
            for(String category: categories.split(" "))
            {
                searchedBooks.addAll(bookService.searchByCategory1(category));
            }
            return searchedBooks;
        }

        else if(categories!="" && name!="")
        {
            System.out.println("categori name");
            searchedBooks.addAll(bookService.searchByName1(name));
            for(String category: categories.split(" "))
            {
                searchedBooks.addAll(bookService.searchByCategory1(category));
            }
            return searchedBooks;
        }
        else if(name!=""){
            searchedBooks.addAll(bookService.searchByName1(name));
            return searchedBooks;
        }
        else if(author!=""){
            searchedBooks.addAll(bookService.searchByAuthor(author));
            return searchedBooks;
        }
        else if(categories!=""){
            for(String category: categories.split(" "))
            {
                searchedBooks.addAll(bookService.searchByCategory1(category));
            }
            return searchedBooks;
        }
        else if (categories=="" && name=="" && author==""){

            System.out.println("koulchi null");
             searchedBooks.addAll(bookService.getAllBooks());
            return searchedBooks;
        }

        return Collections.EMPTY_SET;
    }

}
