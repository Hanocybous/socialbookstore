package myy803.socialbookstore.mapper;

import myy803.socialbookstore.model.entities.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface is used to map the BookCategory class to the database.
 */
@Repository
@Component
public interface BookCategoryMapper extends JpaRepository<BookCategory, Integer> {

    /**
     * This method is used to find a book category by its name.
     *
     * @param name The name of the book category.
     * @return A list of book categories with the given name.
     */
    List<BookCategory> findByName(String name);
}
