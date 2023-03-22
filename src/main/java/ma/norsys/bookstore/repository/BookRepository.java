package ma.norsys.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.norsys.bookstore.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findBookByNameContaining(String name);

    List<Book> findBookByCategory(String category);

}
