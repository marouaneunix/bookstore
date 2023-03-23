package ma.norsys.bookstore.Services;

import ma.norsys.bookstore.Entities.Book;
import ma.norsys.bookstore.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class BookServiceImpl implements BookServiceInterface{

    @Autowired
    private BookRepository bookRepository;


    @Override
    public Book saveBook(Book book) {

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
    public HashSet<Book> searchByCategory(String categories) {
        HashSet<Book> bookFound =new HashSet<>();
        List<Book> allBook=bookRepository.findAll();
        String[] categoriesSearchSplit=categories.split(" ");

       for(int i=0;i< categoriesSearchSplit.length;i++){
           for(int j=0; j<allBook.size();j++){
                  //  if(allBook.get(j).getCategory().contains(categoriesSearchSplit[i])){
                    //    bookFound.add(allBook.get(j));
                   // }
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
