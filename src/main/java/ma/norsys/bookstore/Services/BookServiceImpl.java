package ma.norsys.bookstore.Services;

import ma.norsys.bookstore.Entities.Book;
import ma.norsys.bookstore.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.*;

@Service
public class BookServiceImpl implements BookServiceInterface{

    @Autowired
    private BookRepository bookRepository;


    @Override
    public Book saveBook(Book book) {
        Objects.requireNonNull(book, "Book should not be null");
        Objects.requireNonNull(book.getName(), "Book name should not be null");
        Objects.requireNonNull(book.getIsbn(), "Book isbn should not be null");
        Objects.requireNonNull(book.getAuthor(), "Book Author should not be null");
        Objects.requireNonNull(book.getDescription(), "Book Description should not be null");
        return bookRepository.save(book);
    }

    @Override
    public void deleteBookById(Long id) {
            bookRepository.deleteById(id);
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchByName(String name) {
        return bookRepository.getBookByName(name);
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }


    public List<Book> searchByName1(String name) {

        return bookRepository.findBooksByNameContainsIgnoreCase(name);
   }


   public List<Book> searchByCategory1(String category) {
        return bookRepository.findBooksByCategoryContainsIgnoreCase(category);
   }


    public List<Book> searchByAuthor(String author) {
        return bookRepository.findBooksByAuthorContainsIgnoreCase(author);
    }



//    public List<Book> searchByNameByCategory1(String name,String category)
//    {
//        return bookRepository.findBookByNameContainingIgnoreCaseAndCategoryContainingIgnoreCase(name,category);
//    }
//    @Override
//    public List<Book> findBookByNameContainingIgnoreCase(String name){
//
//        return bookRepository.findBookByNameContainingIgnoreCase(name);
//    }
    @Override
    public HashSet<Book> searchByCategory(String categories) {
        HashSet<Book> bookFound =new HashSet<>();
        List<Book> allBook=bookRepository.findAll();
        String[] categoriesSearchSplit=categories.split(" ");

       for(int i=0;i< categoriesSearchSplit.length;i++){
           for(int j=0; j<allBook.size();j++){

               String[] bookCategorySplit=allBook.get(j).getCategory().split(" ");


               for(int k=0;k<bookCategorySplit.length;k++){


                   if(bookCategorySplit[k].contains(categoriesSearchSplit[i])){



                       bookFound.add(allBook.get(j));
                   }



               }

           }
       }
       return bookFound;
    }
}
