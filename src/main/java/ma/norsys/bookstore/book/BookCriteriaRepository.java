package ma.norsys.bookstore.book;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class BookCriteriaRepository {

    private final EntityManager entityManager;

    public BookCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Book> search(String name, List<String> categories) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> book = criteriaQuery.from(Book.class);

        List<Predicate> predicates = new ArrayList<>();

        if (name != null && !name.isEmpty() && !name.isBlank()) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.upper(book.get("name")), "%" + name.toUpperCase() + "%"));
        }

        if (categories != null && !categories.isEmpty()) {
            predicates.add(book.join("categories").in(categories));
        }

        criteriaQuery.select(book).where(predicates.toArray(new Predicate[]{}));


        Query query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
