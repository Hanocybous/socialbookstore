package myy803.socialbookstore.mapper;

import myy803.socialbookstore.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This interface is used to interact with the database and perform CRUD operations on the User table.
 */
@Repository
@Component
public interface UserMapper extends JpaRepository<User, String> {

    /**
     * This method is used to find a user by their username.
     *
     * @param username The username of the user to be found.
     * @return An optional containing the user if found, or an empty optional if not found.
     */
    Optional<User> findByUsername(String username);

}
