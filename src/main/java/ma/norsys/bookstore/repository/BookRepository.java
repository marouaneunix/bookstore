package ma.norsys.bookstore.repository;

import ma.norsys.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    public List<Book> findBookByNameContainingIgnoreCase(String nameTerm);
    public List<Book> findBookByCategoryContainingIgnoreCase(String categoryTerm);
    public List<Book> findBookByNameContainingIgnoreCaseAndCategoryContainingIgnoreCase(String nameTerm,String categoryTerm);
}
