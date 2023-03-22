package ma.norsys.bookstore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "book")
public class Book {
    @Id
    private int id;

    private String ISBN;
    private String name;
    private String author;
    private String category;
    private String description;

}
