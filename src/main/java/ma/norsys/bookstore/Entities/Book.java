package ma.norsys.bookstore.Entities;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;



import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_book")
    private Long idBook;

    @Column( nullable = false, unique = true)
    private String name;

    @Column( nullable = false)
    private String category;

    @Column( nullable = false)
    private String author;

    @Column( nullable = false, unique = true)
    private String isbn;

    @Column( nullable = false)
    private String description;

}
