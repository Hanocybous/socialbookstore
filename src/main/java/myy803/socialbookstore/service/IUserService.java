package myy803.socialbookstore.service;

import myy803.socialbookstore.model.entities.User;

/**
 * Service interface for handling user operations.
 * <p>
 * This service provides methods to save a user, check if a user is present,
 * and find a user by their username.
 * </p>
 *
 */
public interface IUserService {


    /**
     * @param user User to be saved
     */
    void saveUser(User user);

    /**
     * @param user User to be checked
     * @return true if user is present in the database
     */
    boolean isUserPresent(User user);

    /**
     * @param username Username of the user to be found
     * @return User with the given username
     */
    User findById(String username);
}
