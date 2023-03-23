package ma.norsys.bookstore.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private long id;

 @Column(length = 10 , nullable = false)
 private String title;

 @Column(length = 25 ,nullable = false ,unique = true)
 private String isbn;

 @Column(length = 25 ,nullable = false)
 private String auteur;

 @Column(length = 100 )
 private String description;

 @Column(length = 100 ,nullable = false)
 private String categories;


// @ManyToMany
// @JoinTable(
//         name = "categorie",
//         joinColumns = @JoinColumn(name = "id"),
//         inverseJoinColumns = @JoinColumn(name = "categorie"))
// private List<Categorie> categories;


}
