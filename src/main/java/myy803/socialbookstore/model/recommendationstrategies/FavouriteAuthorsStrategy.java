package myy803.socialbookstore.model.recommendationstrategies;

import myy803.socialbookstore.dto.BookDto;
import myy803.socialbookstore.mapper.UserProfileMapper;
import myy803.socialbookstore.model.entities.UserProfile;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Strategy for recommending books based on the user's favorite authors.
 * <p>
 * This strategy retrieves books from the user's favorite authors and returns them as recommendations.
 * </p>
 *
 * @see AbstractRecommendationStrategy
 * @see UserProfile
 * @see BookDto
 * @see UserProfileMapper
 */
@Component
public class FavouriteAuthorsStrategy extends AbstractRecommendationStrategy {

    public FavouriteAuthorsStrategy(UserProfileMapper userProfileMapper) {
        super(userProfileMapper);
    }

    @Override
    protected List<BookDto> retrieveRecommendedBooks(@NotNull UserProfile userProfile) {
        return userProfile.getBooksFromFavouriteAuthors();
    }

}