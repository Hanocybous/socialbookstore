package myy803.socialbookstore.model.recommendationstrategies;

import myy803.socialbookstore.dto.BookDto;
import myy803.socialbookstore.mapper.UserProfileMapper;
import myy803.socialbookstore.model.entities.UserProfile;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Strategy for recommending books based on the user's favorite categories and authors.
 * <p>
 * This strategy retrieves books from the user's favorite authors and favorite categories,
 * and returns them as recommendations.
 * </p>
 *
 * @see UserProfile
 * @see BookDto
 * @see AbstractRecommendationStrategy
 * @see UserProfileMapper
 */
@Component
@Transactional
public class FavouriteCategoriesAndAuthorsStrategy extends AbstractRecommendationStrategy {

    public FavouriteCategoriesAndAuthorsStrategy(UserProfileMapper userProfileMapper) {
        super(userProfileMapper);
    }

    @Override
    protected List<BookDto> retrieveRecommendedBooks(@NotNull UserProfile userProfile) {
        List<BookDto> bookDtos = new ArrayList<>();
        bookDtos.addAll(userProfile.getBooksFromFavouriteAuthors());
        bookDtos.addAll(userProfile.getBooksOfFavouriteCategories());
        return bookDtos;
    }
}