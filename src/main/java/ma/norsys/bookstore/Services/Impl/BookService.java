package ma.norsys.bookstore.Services.Impl;

import ma.norsys.bookstore.Exceptions.BookNotFoundException;
import ma.norsys.bookstore.Models.Book;
import ma.norsys.bookstore.Repository.BookRepository;
import ma.norsys.bookstore.Services.InterfaceServiceBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class BookService implements InterfaceServiceBook {


    @Autowired
    private BookRepository bookRepo;

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

//    public List<Book> getAllBookByTitleAndCategory(String title, String category) {
//        List<Book> foundsBook = new ArrayList<>();
//        if (title != null && !title.isEmpty() && category != null && !category.isEmpty()) {
//            foundsBook = bookRepo.findBookByTitleStartsWithAndCategory(title, category);
//        } else if (title == null || title.isEmpty()) {
//            foundsBook = bookRepo.getAllBookByCategoryContaining(category);
//        } else if (category == null || category.isEmpty()) {
//            foundsBook = bookRepo.findBookByTitleStartsWith(title);
//        } else {
//            foundsBook = bookRepo.findAll();
//        }
//        return foundsBook;
//    }







    public List<Book> getAllBookByTitleAndCategory(String title, String categorie) {

        List<Book> foundBook=new ArrayList<>();
        boolean test = false;
        if(title != null && categorie!=null) {
            List<Book> list = bookRepo.findBookByTitleStartsWith(title);
            for (Book a : list) {
                String[] categoriesSearchSplit = categorie.split(" ");
                for(String i : categoriesSearchSplit){
                    String[] categoriesBook = a.getCategories().split(" ");
                    for(String j : categoriesBook){
                        if( i.equals(j) ) {
                            test = true;
                            break;
                        }else test = false;
                    }

                }
                if(test == true) foundBook.add(a);
            }
        }
        else if ((title == null || title.isEmpty() )  && (categorie != null  && !categorie.isEmpty())) {
                foundBook = bookRepo.getAllBookByCategoryContaining(categorie);
            }
        else if((categorie == null || categorie.isEmpty()) && (title != null  && !title.isEmpty())){
            foundBook=bookRepo.findBookByTitleStartsWith(title);
        }
        else{
            foundBook=bookRepo.findAll();
        }

        return foundBook;


    }
}
