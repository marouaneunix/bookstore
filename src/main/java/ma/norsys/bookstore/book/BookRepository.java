package ma.norsys.bookstore.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


    @Query("""
            SELECT b FROM Book b
            where UPPER(b.name) LIKE UPPER(concat('%',:name,'%'))
            AND ELEMENTS(b.categories) IN :categories
            """)
    List<Book> searchByCriteria(@Param("name") String name, @Param("categories") List<String> categories);

    List<Book> findByNameContainingIgnoreCase(String name);

    List<Book> findByCategoriesIn(List<String> categories);
}
