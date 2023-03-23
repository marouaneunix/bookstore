package ma.norsys.bookstore.services;

import lombok.extern.slf4j.Slf4j;
import ma.norsys.bookstore.exceptions.BookNotFoundException;
import ma.norsys.bookstore.exceptions.InvalidRequestException;
import ma.norsys.bookstore.models.Book;
import ma.norsys.bookstore.models.Category;
import ma.norsys.bookstore.repositories.BooksRepository;
import ma.norsys.bookstore.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class BooksService {

    @Autowired
    private BooksRepository booksRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public void validateBook(Book book) throws InvalidRequestException {
        if (book.getIsbn() == null || book.getIsbn() == null) {
            throw new InvalidRequestException("ISBN is required");
        }
        if (book.getTitle() == null || book.getTitle().isEmpty()) {
            throw new InvalidRequestException("Title is required");
        }
        if (book.getAuthor() == null || book.getAuthor().isEmpty()) {
            throw new InvalidRequestException("Author is required");
        }
        if (book.getCategories() == null || book.getCategories().isEmpty()) {
            throw new InvalidRequestException("Book should have at least on category");
        }
        if (book.getDescription().length() < 10) {
            throw new InvalidRequestException("Short description");
        }

        if (booksRepository.findByIsbn(book.getIsbn()).isPresent()) {
            throw new InvalidRequestException("Book with " + book.getIsbn() + " already exists");
        }
    }

    public Book save(Book book) throws InvalidRequestException {

        validateBook(book);

        List<Category> categories = new ArrayList<>();
        Category bookCategory;
        for (Category category : book.getCategories()) {
            Optional<Category> existingCategory = categoryRepository.findByName(category.getName());
            if (!existingCategory.isPresent()) {
                bookCategory = categoryRepository.save(category);
            } else {
                bookCategory = existingCategory.get();
            }
            categories.add(bookCategory);
        }

        book.setCategories(categories);
        return booksRepository.save(book);
    }

    public Book update(Book book) throws BookNotFoundException {

        try {

            validateBook(book);

            return booksRepository.save(book);

        } catch (Exception e) {
            throw new BookNotFoundException("Book doesn't exist");
        }
    }

    public List<Book> getAll() {
        return booksRepository.findAll();
    }

    public Book getById(Long id) throws BookNotFoundException {
        Optional<Book> optionalBook = booksRepository.findById(id);
        if (!optionalBook.isPresent()) {
            throw new BookNotFoundException("Book doesn't exist");
        }
        return optionalBook.get();
    }

    public List<Book> search(String name, String category) {
        List<Book> books = booksRepository.findByTitleContainingIgnoreCase(name);
        books.addAll(booksRepository.findByCategoriesNameContainingIgnoreCase(category));

        Set<Book> uniqueBooks = new HashSet<>(books);
        return new ArrayList<>(uniqueBooks);
    }

    public String delete(Long id) throws BookNotFoundException {
        Book existingBook = getById(id);
        try {
            booksRepository.delete(existingBook);
            return "Book deleted successfully";

        } catch (Exception e) {
            return e.getMessage();
        }
    }

}