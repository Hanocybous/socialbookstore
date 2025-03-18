package myy803.socialbookstore.model.searchstrategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Factory for creating search strategies.
 *
 * @see SearchStrategy
 * @see ExactSearchStrategy
 * @see ApproximateSearchStrategy
 * @see RatingSearchStrategy
 */
@Component
public class SearchFactory {

    private final ExactSearchStrategy exactSearchStrategy;
    private final ApproximateSearchStrategy approximateSearchStrategy;
    private final RatingSearchStrategy ratingSearchStrategy;

    @Autowired
    public SearchFactory(ExactSearchStrategy exactSearchStrategy, ApproximateSearchStrategy approximateSearchStrategy, RatingSearchStrategy ratingSearchStrategy) {
        this.exactSearchStrategy = exactSearchStrategy;
        this.approximateSearchStrategy = approximateSearchStrategy;
        this.ratingSearchStrategy = ratingSearchStrategy;
    }

    public SearchStrategy create(String searchStrategy) {
        if (searchStrategy == null) {
            return approximateSearchStrategy;
        }
        return switch (searchStrategy) {
            case "Exact" -> exactSearchStrategy;
            case "Rating" -> ratingSearchStrategy;
            default -> approximateSearchStrategy;
        };
    }
}
