package ma.norsys.bookstore.services;

import ma.norsys.bookstore.exceptions.BookNotFoundException;
import com.example.evaluation_livre.services.IBookService;
import ma.norsys.bookstore.exceptions.PostArgumentNotValidException;
import ma.norsys.bookstore.models.Book;
import ma.norsys.bookstore.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImp implements IBookService {


    private final BookRepository bookRepository;

    public BookServiceImp(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book getById(int id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Not found Book with id :" + id));
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book addBook(Book book) throws PostArgumentNotValidException {
        Objects.requireNonNull(book, "Book should not be null");
        Objects.requireNonNull(book.getTitle(), "Book name should not be null");
        Objects.requireNonNull(book.getIsbn(), "Book Isbn should not be null");
        Objects.requireNonNull(book.getCategories(), "Book categories should not be null");
        Objects.requireNonNull(book.getAuthor(), "Book Author should not be null");
        return bookRepository.save(book);
    }

    @Override
    public boolean deleteBook(int id) {
        bookRepository.deleteById(id);
        return !bookRepository.existsById(id);
    }

    @Override
    public Set<Book> getByTitleOrCategoriesOrBoth(String title, String categories, String author) {
        Set<Book> matchingBooks = new HashSet<>();
        List<String> cats = List.of(categories.split(" "));

        boolean b1 = title == null || title.isEmpty() || title.isBlank();
        boolean b2 = categories == null || categories.isEmpty() || categories.isBlank();
        List<Book> books = bookRepository.findAll();

        if(author !=""){
            books.forEach(book -> {
                if (book.getAuthor().toUpperCase().contains(author.toUpperCase())) {
                    matchingBooks.add(book);
                }
            });
            return matchingBooks;
        }else{
        if (b1 && b2) return new HashSet<>(books);
        if (b1) {
            for (String cat : cats) {
                books.forEach(book -> {
                    if (book.getCategories().toUpperCase().contains(cat.toUpperCase())) {
                        matchingBooks.add(book);
                    }
                });
            }
            return matchingBooks;
        }
        if (b2) {
            books.forEach(book -> {
                if (book.getTitle().toUpperCase().contains(title.toUpperCase())) {
                    matchingBooks.add(book);
                }
            });
            return matchingBooks;
        }
        for (String cat : cats) {
            books.forEach(book -> {
                if (book.getCategories().toUpperCase().contains(cat.toUpperCase()) &&
                        book.getTitle().toUpperCase().contains(title.toUpperCase())) {
                    matchingBooks.add(book);
                }
            });
        }
        if (matchingBooks.isEmpty()) throw new BookNotFoundException("No books with given categories or title");
        return matchingBooks;
    }}

    @Override
    public void updateBook(Book book) {
        bookRepository.save(book);
    }
}


