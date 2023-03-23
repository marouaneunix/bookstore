package ma.norsys.bookstore.Repositories;

import ma.norsys.bookstore.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {




    @Query( "SELECT b FROM Book b  WHERE b.name like %:name%" )
    List<Book> getBookByName( @Param("name") String name);




}
