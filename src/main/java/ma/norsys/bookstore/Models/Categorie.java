package ma.norsys.bookstore.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;



public class Categorie {


    private String name;

//    @ManyToMany(mappedBy = "categories")
//    private List<Book> books;



}
