package ma.norsys.bookstore.services;
import ma.norsys.bookstore.entities.Book;
import ma.norsys.bookstore.exception.BookNotFoundException;
import java.util.List;
import java.util.Set;


public interface BookService {
    public Book createBook(Book book);
    public Book updateBook(Book book);
    public void deleteBook(Book book);
    public void deleteBookById(Long idBook);
    public Book getBookById(Long idBook) throws BookNotFoundException;
    public List<Book> getAllBooks();
    public Set<Book> getBooksByCategoriesContain(String searchTerm);
    public Set<Book> getBooksByNameContain(String searchTerm);
    public Set<Book> getBooksByNameAndCategory(String searchTerm,String catSearchTerm);
}
