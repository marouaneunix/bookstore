package ma.norsys.bookstore.repository;

import ma.norsys.bookstore.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Set<Book> findByNameIgnoreCaseContaining(String searchTerm);
    Set<Book> findByCategorieIgnoreCaseContaining(String searchTerm);
    Set<Book> findBooksByNameContainingIgnoreCaseAndCategorieContainingIgnoreCase(String searchTerm,String catSearchTerm);

}
