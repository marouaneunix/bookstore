package ma.norsys.bookstore.Services;
import ma.norsys.bookstore.Entity.Livre;
import ma.norsys.bookstore.Exception.NullLivreException;
import ma.norsys.bookstore.Repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class LivreServiceImpl implements LivreService{
    @Autowired
    private LivreRepository livreRepository;

    @Override
    public Livre createLivre(Livre livre) {
        return livreRepository.save(livre);
    }

    @Override
    public Livre getLivreById(int id) throws NullLivreException {
        return livreRepository.findById(id).orElseThrow(()->new NullLivreException("this book is not exist"));
    }

    @Override
    public Livre updateLivre(Livre livre) {
        return livreRepository.save(livre);
    }

    @Override
    public void deleteLivreById(int id) {
        livreRepository.deleteById(id);
    }

    @Override
    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }

    @Override
    public Livre getLivreByName(String name) throws NullLivreException {
        return livreRepository.getLivreByName(name);
    }

    @Override
    public Livre getLivreByCategory(String category) throws NullLivreException {
        return livreRepository.getLivreByCategory(category);
    }
}
