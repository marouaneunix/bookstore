package ma.norsys.bookstore.service;

import ma.norsys.bookstore.entity.Book;

import java.util.List;

public interface BookService {
    public Book createBook(Book book);
    public Book getBook(int id) throws Exception;
    public Book update(Book book) throws Exception;
    public List<Book> getAllBook();
    public void deleteBook(int id);
    public List<Book> searchByName(String name);
    public List<Book> searchByCategory(String category);

    public List<Book> searchByNameByCategory(String name,String category);
}
