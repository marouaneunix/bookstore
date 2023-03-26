package ma.norsys.bookstore.services;

import jakarta.annotation.Nullable;
import ma.norsys.bookstore.exceptions.BookNotFoundException;
import ma.norsys.bookstore.models.Book;
import ma.norsys.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookServiceImp  implements IBookService {
    @Autowired
    BookRepository bookRepository;

    public Book getById(int id) {
        return bookRepository.findById(id)
                .orElseThrow(()->new BookNotFoundException("Not found Book with id :"+id));
    }



    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public void addBook(Book book){

        Objects.requireNonNull(book, "Book should not be null");
        Objects.requireNonNull(book.getTitle(), "Book name should not be null");
        Objects.requireNonNull(book.getIsbn(), "Book isbn should not be null");
        Objects.requireNonNull(book.getAuthor(), "Book author should not be null");
        Objects.requireNonNull(book.getCategories(), "Book must have at least one category");
        Objects.requireNonNull(book.getDescription(), "Book description have at least one category");
        bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book Book) {
        return bookRepository.save(Book);
    }

    @Override
    public Set<Book> findByTitle(String title) {
        return bookRepository.findBooksByTitleContaining(title);
    }

    @Override
    public Set<Book> findByCategories(String categories) {
        String[] listCategories = categories.split(", ");
        List<Book> lists = bookRepository.findAll();
        Set<Book> books = new HashSet<>();
        for(Book book:lists){
            for(String s: listCategories){
                if(book.getCategories().toUpperCase().contains(s.toUpperCase())){
                    books.add(book);
                }
            }
        }
        return books;
    }


    @Override
    public Set<Book> findByCategoriesAndTitle(@Nullable String categories, @Nullable String title) {
        if((title== null ||title.isEmpty() || title.isBlank())  &&
                (categories==null || categories.isEmpty() || categories.isBlank()))
            return new HashSet<> (getAll());
        if(title== null ||title.isEmpty() || title.isBlank()) return findByCategories(categories);
        if(categories==null || categories.isEmpty() || categories.isBlank()) return findByTitle(title);
        Set<Book> newSet = new HashSet<> (findByCategories (categories));
        newSet.retainAll(findByTitle(title));
        return newSet;
    }

    @Override
    public boolean deleteBook(int id) {
        bookRepository.deleteById(id);
        return !bookRepository.existsById(id);
    }


}
