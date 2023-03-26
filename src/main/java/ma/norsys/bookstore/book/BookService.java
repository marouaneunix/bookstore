package ma.norsys.bookstore.book;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BookService {

    private final BookRepository bookRepository;



    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public void save(Book book) {
        Objects.requireNonNull(book, "Book should not be null");
        Objects.requireNonNull(book.getName(), "Book name should not be null");
        this.bookRepository.save(book);
    }

    public List<Book> searchByNameAndCategories(String name, List<String> categories) {
        return this.bookRepository.searchByCriteria(name, categories);
    }

    public List<Book> searchByName(String name) {
        return this.bookRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Book> searchByCategories(List<String> categories) {
        return this.bookRepository.findByCategoriesIn(categories);
    }
}
