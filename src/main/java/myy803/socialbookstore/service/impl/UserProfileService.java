package myy803.socialbookstore.service.impl;

import myy803.socialbookstore.dto.UserProfileDto;
import myy803.socialbookstore.mapper.BookAuthorMapper;
import myy803.socialbookstore.mapper.BookCategoryMapper;
import myy803.socialbookstore.mapper.UserProfileMapper;
import myy803.socialbookstore.model.entities.BookCategory;
import myy803.socialbookstore.model.entities.UserProfile;
import myy803.socialbookstore.service.IUserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service implementation for handling user profiles.
 * <p>
 * This service provides methods to get a user profile, get all book categories,
 * and save a user profile.
 * </p>
 *
 * @see IUserProfileService
 * @see UserProfileMapper
 * @see BookCategoryMapper
 * @see BookAuthorMapper
 * @see UserProfile
 * @see BookCategory
 * @see UserProfileDto
 */
@Service
public class UserProfileService implements IUserProfileService {

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Autowired
    private BookCategoryMapper bookCategoryMapper;

    @Autowired
    private BookAuthorMapper bookAuthorMapper;

    /**
     * Gets a user profile by username.
     *
     * @param username the username of the user
     * @return the user profile
     */
    public UserProfileDto getUserProfile(String username) {
        Optional<UserProfile> optUserProfile = userProfileMapper.findById(username);
        return optUserProfile.map(UserProfile::buildProfileDto)
                .orElseGet(() -> new UserProfileDto(username));
    }

    /**
     * Gets all book categories.
     *
     * @return the list of book categories
     */
    public List<BookCategory> getAllBookCategories() {
        return bookCategoryMapper.findAll();
    }

    /**
     * Saves a user profile.
     *
     * @param userProfileDto the user profile dto
     */
    public void saveUserProfile(UserProfileDto userProfileDto) {
        Optional<UserProfile> optUserProfile = userProfileMapper.findById(userProfileDto.getUsername());
        UserProfile userProfile = optUserProfile.orElseGet(UserProfile::new);
        userProfileDto.buildUserProfile(userProfile, bookAuthorMapper, bookCategoryMapper);
        userProfileMapper.save(userProfile);
    }

    public UserProfile findByUsername(String username) {
        return userProfileMapper.findById(username).orElse(null);
    }
}
