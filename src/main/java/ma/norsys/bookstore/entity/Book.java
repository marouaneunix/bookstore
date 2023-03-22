package ma.norsys.bookstore.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String Isbn;


    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String year;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String descreption;


}
