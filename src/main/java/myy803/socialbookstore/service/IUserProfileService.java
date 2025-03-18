package myy803.socialbookstore.service;

import myy803.socialbookstore.dto.UserProfileDto;
import myy803.socialbookstore.model.entities.BookCategory;

import java.util.List;

/**
 * Service interface for handling user profiles.
 * <p>
 * This service provides methods to get a user profile, get all book categories,
 * and save a user profile.
 * </p>
 *
 * @see myy803.socialbookstore.dto.UserProfileDto
 * @see myy803.socialbookstore.model.entities.BookCategory
 */
public interface IUserProfileService {

    /**
     * Get the user profile of the user with the given username.
     *
     * @param username the username of the user
     * @return the user profile of the user with the given username
     */
    UserProfileDto getUserProfile(String username);

    /**
     * Get all book categories.
     *
     * @return all book categories
     */
    List<BookCategory> getAllBookCategories();

    /**
     * Save the user profile.
     *
     * @param userProfileDto the user profile to save
     */
    void saveUserProfile(UserProfileDto userProfileDto);
}