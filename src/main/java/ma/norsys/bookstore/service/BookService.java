package ma.norsys.bookstore.service;
import ma.norsys.bookstore.exception.BookNotFoundException;
import ma.norsys.bookstore.model.Book;
import ma.norsys.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.micrometer.common.lang.Nullable;

import java.util.*;


@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Set<Book> findByCategories(String categories) {
       String[] listCategories = categories.split(" ");
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
    public Set<Book> findByName(String name) {
       Set<Book> books = new HashSet<>();
       for(Book book: bookRepository.findAll()){
            if(book.getName().toUpperCase().contains(name.toUpperCase())){
                books.add(book);
            }
        }
    return books;
    }

    public Set<Book> findByCategoriesAndName(@Nullable String categories, @Nullable String name) {
        if((name== null ||name.isEmpty() || name.isBlank())  &&
                (categories==null || categories.isEmpty() || categories.isBlank()))
            return new HashSet<> (getAll());
        if(name== null ||name.isEmpty() || name.isBlank()) return findByCategories(categories);
        if(categories==null || categories.isEmpty() || categories.isBlank()) return findByName(name);
        Set<Book> newBookSet = new HashSet<> (findByCategories (categories));
        newBookSet.retainAll(findByName(name));
        return newBookSet;
    }


    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book save(Book book) {
        Objects.requireNonNull(book, "Book should not be null");
        Objects.requireNonNull(book.getName(), "Book name should not be null");
        System.out.println(book.toString());
        return this.bookRepository.save(book);
    }


    public Boolean delete(Long id) throws BookNotFoundException {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (!optionalBook.isPresent()) {
            throw new BookNotFoundException("Book not found");
        }
        bookRepository.deleteById(id);
        return true;
    }

}
