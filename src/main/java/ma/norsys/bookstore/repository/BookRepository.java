package ma.norsys.bookstore.repository;

import ma.norsys.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findBooksByTitleContainsIgnoreCase(String search);
    List<Book> findBooksByCategoryContainsIgnoreCase(String category);
    List<Book> findBooksByTitleContainsIgnoreCaseAndCategoryContainsIgnoreCase(String title,String category);
}
