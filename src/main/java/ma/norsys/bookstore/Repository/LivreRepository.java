package ma.norsys.bookstore.Repository;

import ma.norsys.bookstore.Entity.Livre;
import ma.norsys.bookstore.Exception.NullLivreException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivreRepository extends JpaRepository<Livre,Integer> {
    public Livre getLivreByName(String name) throws NullLivreException;
    public Livre getLivreByCategory(String category) throws NullLivreException;

}
