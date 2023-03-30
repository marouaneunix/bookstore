package ma.norsys.bookstore.services;

import ma.norsys.bookstore.entities.Book;
import ma.norsys.bookstore.exception.BookNotFoundException;
import ma.norsys.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book createBook(Book book) {
        Objects.requireNonNull(book, "Book should not be null");
        Objects.requireNonNull(book.getName(), "Book name should not be null");
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book) {
        Objects.requireNonNull(book, "Book should not be null");
        Objects.requireNonNull(book.getName(), "Book name should not be null");
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }

    @Override
    public void deleteBookById(Long idBook) {
        bookRepository.deleteById(idBook);
    }

    @Override
    public Book getBookById(Long idBook) throws BookNotFoundException {
        return bookRepository.findById(idBook).orElseThrow(()->new BookNotFoundException("this book is not exist"));
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Set<Book> getBooksByNameContain(String searchTerm) {
        return bookRepository.findByNameIgnoreCaseContaining(searchTerm);
    }

    @Override
    public Set<Book> getBooksByNameAndCategory(String searchTerm, String catSearchTerm) {
        return bookRepository.findBooksByNameContainingIgnoreCaseAndCategorieContainingIgnoreCase(searchTerm,catSearchTerm);
    }

    @Override
    public Set<Book> getBooksByAuthorContain(String searchTerm) {
        return bookRepository.findBooksByAuthorIgnoreCaseContaining(searchTerm);
    }

    @Override
    public Set<Book> getBooksByCategoriesContain(String searchTerm) {
        return bookRepository.findByCategorieIgnoreCaseContaining(searchTerm);
    }
}
