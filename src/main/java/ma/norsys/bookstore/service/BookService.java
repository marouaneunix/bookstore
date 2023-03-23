package ma.norsys.bookstore.service;

import ma.norsys.bookstore.models.Book;
import ma.norsys.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(int bookId) {
        return bookRepository.findById(bookId);
    }


    public Book createProduct(Book book) {
        return bookRepository.save(book);
    }


}





