package myy803.socialbookstore.service;

import myy803.socialbookstore.model.entities.User;


/**
 * Service for authentication. It provides methods for checking if a user is present in the database and for saving a user.
 * <p>
 *     The user is present in the database if the username and password match the ones in the database.
 *     The user is saved in the database if the username is not already present.
 * </p>
 */
public interface IAuthService {

    /**
     * Checks if the user is present in the database.
     *
     * @param user the user to check
     * @return true if the user is present, false otherwise
     */
    boolean isUserPresent(User user);

    /**
     * Saves the user in the database.
     *
     * @param user the user to save
     */
    void saveUser(User user);

}
