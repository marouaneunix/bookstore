package ma.norsys.bookstore.book;

import jakarta.persistence.*;
import org.springframework.context.annotation.Primary;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "")

    private String name;

    public Book(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Book() {
    }

    public Book(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
