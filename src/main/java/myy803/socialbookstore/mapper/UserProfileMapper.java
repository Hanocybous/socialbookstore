package myy803.socialbookstore.mapper;

import myy803.socialbookstore.model.entities.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * This interface is used to interact with the database to perform CRUD operations on the UserProfile entity.
 */
@Repository
@Component
public interface UserProfileMapper extends JpaRepository<UserProfile, String> {

    /**
     * This method is used to find a UserProfile by its username.
     *
     * @param username The username of the UserProfile to be found.
     * @return The UserProfile with the given username.
     */
    UserProfile findByUsername(String username);
}
