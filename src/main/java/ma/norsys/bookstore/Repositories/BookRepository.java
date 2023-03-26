package ma.norsys.bookstore.Repositories;

import ma.norsys.bookstore.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book,Long> {




    @Query( "SELECT b FROM Book b  WHERE b.name like %:name%" )
    List<Book> getBookByName( @Param("name") String name);








}
