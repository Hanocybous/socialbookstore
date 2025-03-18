package myy803.socialbookstore.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

/**
 * Entity class representing a Book Category.
 * <p>
 * This class is annotated with JPA annotations to map it to a database table.
 * It includes fields for the category's ID, name, and the list of books that belong to this category.
 * </p>
 *
 * @see Book
 */
@Setter
@Getter
@RequiredArgsConstructor
@ToString(exclude = "bookOffers")
@Entity
@Table(name = "book_categories")
public class BookCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Book> bookOffers;

    @Override
    public int hashCode() {
        return Objects.hash(categoryId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BookCategory other = (BookCategory) obj;
        return categoryId == other.categoryId;
    }

    public boolean checkName(String categoryName) {
        return name.equals(categoryName);
    }
}
