package ma.norsys.bookstore.Services.Impl;

import ma.norsys.bookstore.Exceptions.BookNotFoundException;
import ma.norsys.bookstore.Models.Book;
import ma.norsys.bookstore.Repos.BookRepo;
import ma.norsys.bookstore.Services.InterfaceServiceBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class BookService implements InterfaceServiceBook {


    @Autowired
    private BookRepo bookRepo;

    @Override
    public Book addBook(Book book) {
        return bookRepo.save(book);
    }


    @Override
    public void deleteBook(long id) throws BookNotFoundException {
         bookRepo.deleteById(id);
    }



    @Override
    public List<Book> findBookByTitle(String title) throws BookNotFoundException {
        List<Book> books = (List<Book>) bookRepo.findBookByTitleStartsWith(title);
        if(!books.isEmpty()) {
            return books;
        } else {
            throw new BookNotFoundException("not found book with title =" + title);
        }
    }

    @Override
    public List<Book> getAllBooks()  {
        return bookRepo.findAll();
    }

    @Override
    public HashSet<Book> getAllBookByCategoryContaining(String categorie) {
        HashSet<Book> bookFound =new HashSet<>();
        List<Book> allBook=bookRepo.findAll();
        String[] categoriesSearchSplit=categorie.split(" ");
        for(int i=0;i< categoriesSearchSplit.length;i++){
            for(int j=0; j<allBook.size();j++){
                  if(allBook.get(j).getCategories().contains(categoriesSearchSplit[i])){
                    bookFound.add(allBook.get(j));
                 }
                  String[] bookCategorySplit=allBook.get(j).getCategories().split(" ");
                 for(int k=0;k<bookCategorySplit.length;k++){
                 if(bookCategorySplit[k].contains(categoriesSearchSplit[i])){
                 bookFound.add(allBook.get(j));
                 }
                 }       }   }
                 return bookFound;






    }
}
