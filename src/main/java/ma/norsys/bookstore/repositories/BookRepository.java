package ma.norsys.bookstore.repositories;

import ma.norsys.bookstore.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    public Set<Book> findBooksByTitleContaining(String title);
}
