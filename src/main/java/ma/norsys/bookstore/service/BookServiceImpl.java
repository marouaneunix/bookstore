package ma.norsys.bookstore.service;

import ma.norsys.bookstore.entity.Book;
import ma.norsys.bookstore.exception.BookNotFoundException;
import ma.norsys.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;


    @Autowired
    public BookServiceImpl(BookRepository employeeRepository) {
        super();
        this.bookRepository = employeeRepository;}

    @Override
    public Book addBook(Book book) {
        Objects.requireNonNull(book, "Book should not be null");
        Objects.requireNonNull(book.getTitle(), "Book name should not be null");
        Objects.requireNonNull(book.getIsbn(), "Book isbn should not be null");
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(long id) throws BookNotFoundException {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBookById(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> findBooksBySearchTerm(String search) {
        return bookRepository.findBooksByTitleContainsIgnoreCase(search);
    }

    @Override
    public List<Book> findBooksByTitleAndCategory(String title, String category) {
        return bookRepository.findBooksByTitleContainsIgnoreCaseAndCategoryContainsIgnoreCase(title,category);
    }

    @Override
    public List<Book> findBooksByCategory(String category) {
        return bookRepository.findBooksByCategoryContainsIgnoreCase(category);
    }

}
