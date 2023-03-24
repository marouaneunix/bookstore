package ma.norsys.bookstore.service;

import ma.norsys.bookstore.entity.Book;
import ma.norsys.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;
    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book getBook(int id) throws Exception {
        return bookRepository.findById(id).orElseThrow(()->new Exception("book dos not exist"));
    }

    @Override
    public Book update(Book book) throws Exception {
        Book updatedBook=getBook(book.getId());
        return bookRepository.save(updatedBook);
    }

    @Override
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> searchByName(String name) {

        return bookRepository.findBookByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Book> searchByCategory(String category) {
        return bookRepository.findBookByCategoryContainingIgnoreCase(category);
    }
    @Override
    public List<Book> searchByNameByCategory(String name,String category)
    {
        return bookRepository.findBookByNameContainingIgnoreCaseAndCategoryContainingIgnoreCase(name,category);
    }
}
