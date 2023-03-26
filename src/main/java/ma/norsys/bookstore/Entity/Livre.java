package ma.norsys.bookstore.Entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name="livre")
@AllArgsConstructor
@NoArgsConstructor
public class Livre {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String auteur;
    private String category;
    private String isbn;
    private String description;

}
