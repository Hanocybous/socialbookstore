package myy803.socialbookstore.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Entity class representing a Book Author.
 * <p>
 * This class is annotated with JPA annotations to map it to a database table.
 * It includes fields for the author's ID, name, and the list of books they have authored.
 * </p>
 *
 * @see Book
 * @see Entity
 */
@Setter
@Getter
@RequiredArgsConstructor
@ToString(exclude = "books")
@Entity
@Table(name = "book_authors")
public class BookAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private int authorId;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "bookAuthors")
    private List<Book> books = new ArrayList<>();

    public BookAuthor(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BookAuthor other = (BookAuthor) obj;
        return authorId == other.authorId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId);
    }
}
