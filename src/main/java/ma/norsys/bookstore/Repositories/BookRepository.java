package ma.norsys.bookstore.Repositories;

import ma.norsys.bookstore.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
