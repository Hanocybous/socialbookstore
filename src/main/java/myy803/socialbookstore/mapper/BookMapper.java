package myy803.socialbookstore.mapper;

import myy803.socialbookstore.model.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface is used to map the Book class to the database.
 */
@Repository
@Component
public interface BookMapper extends JpaRepository<Book, Integer> {

    /**
     * This method is used to find a book by its title.
     *
     * @param title The title of the book.
     * @return A list of books with the given title.
     */
    List<Book> findByTitle(String title);

    List<Book> findByTitleContaining(String title);

    List<Book> findByRatingGreaterThan(double rating);

}
