package ma.norsys.bookstore.Services;
import ma.norsys.bookstore.Entity.Livre;
import ma.norsys.bookstore.Exception.NullLivreException;

import java.util.*;

public interface LivreService {
    public Livre createLivre(Livre livre);
    public Livre getLivreById(int id) throws NullLivreException;
    public Livre updateLivre(Livre livre);
    public void deleteLivreById(int id);
    public List<Livre> getAllLivres();
    Livre getLivreByName(String name) throws NullLivreException;
    Livre getLivreByCategory(String category) throws NullLivreException;
}
