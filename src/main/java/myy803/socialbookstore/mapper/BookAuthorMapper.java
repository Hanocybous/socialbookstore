package myy803.socialbookstore.mapper;

import myy803.socialbookstore.model.entities.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface is used to map the BookAuthor class to the database.
 * It extends the JpaRepository interface, which provides CRUD operations.
 */
@Repository
@Component
public interface BookAuthorMapper extends JpaRepository<BookAuthor, Integer> {

    /**
     * This method is used to find a book author by name.
     *
     * @param name The name of the book author.
     * @return A list of book authors with the given name.
     */
    List<BookAuthor> findByName(String name);
}
