package myy803.socialbookstore.model.recommendationstrategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Factory class for creating different recommendation strategies.
 * <p>
 * This factory provides methods to create instances of various recommendation strategies
 * based on the provided strategy name.
 * </p>
 *
 * @see RecommendationsStrategy
 * @see FavouriteCategoriesStrategy
 * @see FavouriteAuthorsStrategy
 * @see FavouriteCategoriesAndAuthorsStrategy
 * @see TopRatedBooksStrategy
 */
@Component
public class RecommendationsFactory {

    private final FavouriteCategoriesStrategy favouriteCategoriesStrategy;
    private final FavouriteAuthorsStrategy favouriteAuthorsStrategy;
    private final FavouriteCategoriesAndAuthorsStrategy favouriteCategoriesAndAuthorsStrategy;
    private final TopRatedBooksStrategy topRatedBooksStrategy;

    @Autowired
    public RecommendationsFactory(FavouriteCategoriesStrategy favouriteCategoriesStrategy,
                                  FavouriteAuthorsStrategy favouriteAuthorsStrategy,
                                  FavouriteCategoriesAndAuthorsStrategy favouriteCategoriesAndAuthorsStrategy,
                                  TopRatedBooksStrategy topRatedBooksStrategy) {
        this.favouriteCategoriesStrategy = favouriteCategoriesStrategy;
        this.favouriteAuthorsStrategy = favouriteAuthorsStrategy;
        this.favouriteCategoriesAndAuthorsStrategy = favouriteCategoriesAndAuthorsStrategy;
        this.topRatedBooksStrategy = topRatedBooksStrategy;
    }

    public RecommendationsStrategy create(String recomStrategy) {
        if (recomStrategy == null) {
            return null;
        }
        return switch (recomStrategy) {
            case "Favourite Categories" -> favouriteCategoriesStrategy;
            case "Favourite Authors" -> favouriteAuthorsStrategy;
            case "Top Rated Books" -> topRatedBooksStrategy;
            default -> favouriteCategoriesAndAuthorsStrategy;
        };
    }
}