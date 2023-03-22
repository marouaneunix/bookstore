package ma.norsys.bookstore.service;

import java.util.List;

import ma.norsys.bookstore.entity.Book;

public interface BookService {
    Book saveBook(Book book);

    void deleteBook(int id);

    Book getBookById(int id);

    List<Book> getAllBook();

    Book getBookByName(String name);

    List<Book> getBookByCategory(String category);
}
