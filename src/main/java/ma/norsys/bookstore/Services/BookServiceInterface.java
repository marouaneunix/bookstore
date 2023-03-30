package ma.norsys.bookstore.Services;

import ma.norsys.bookstore.Entities.Book;

import java.util.*;

public interface BookServiceInterface {


    Book saveBook(Book book);

    void deleteBookById(Long id);

    Book getBookById(Long id);

    List<Book> getAllBooks();

    List<Book> searchByName(String name);

    Book updateBook(Book book);


    HashSet <Book> searchByCategory(String categories);


}