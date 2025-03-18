package myy803.socialbookstore.model.recommendationstrategies;

import myy803.socialbookstore.dto.BookDto;
import myy803.socialbookstore.mapper.UserProfileMapper;
import myy803.socialbookstore.model.entities.UserProfile;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Abstract base class for recommendation strategies.
 * <p>
 * This class provides a template for recommendation strategies by implementing the {@link RecommendationsStrategy} interface.
 * It uses a {@link UserProfileMapper} to retrieve user profiles and delegates the actual recommendation logic to subclasses.
 * </p>
 *
 * @see RecommendationsStrategy
 * @see UserProfileMapper
 * @see UserProfile
 * @see BookDto
 */
@Transactional
public abstract class AbstractRecommendationStrategy implements RecommendationsStrategy {

    protected final UserProfileMapper userProfileMapper;

    @Autowired
    protected AbstractRecommendationStrategy(UserProfileMapper userProfileMapper) {
        this.userProfileMapper = userProfileMapper;
    }

    @Override
    public List<BookDto> recommend(String username) {
        UserProfile userProfile = userProfileMapper.findByUsername(username);
        return retrieveRecommendedBooks(userProfile);
    }

    protected abstract List<BookDto> retrieveRecommendedBooks(@NotNull UserProfile userProfile);
}
