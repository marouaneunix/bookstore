package ma.norsys.bookstore.Services;

import ma.norsys.bookstore.Exceptions.BookNotFoundException;
import ma.norsys.bookstore.Models.Book;

import java.util.HashSet;
import java.util.List;

public interface InterfaceServiceBook {

    Book addBook(Book book);

    void deleteBook(long id) throws BookNotFoundException;

    List<Book> findBookByTitle(String title) throws BookNotFoundException;

    List<Book> getAllBooks();

    HashSet<Book> getAllBookByCategoryContaining(String categorie);


}
