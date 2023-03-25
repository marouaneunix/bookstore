package ma.norsys.bookstore.service;

import ma.norsys.bookstore.entity.Book;
import ma.norsys.bookstore.exception.BookNotFoundException;

import java.util.List;

public interface BookService {
    Book addBook(Book book);
    Book getBookById(long id) throws BookNotFoundException;
    List<Book> getAllBooks();
    Book updateBook(Book book);
    void deleteBookById(long id);
    List<Book> findBooksBySearchTerm(String search);
    List<Book> findBooksByTitleAndCategory(String title,String category);
    List<Book> findBooksByCategory(String category);



}
