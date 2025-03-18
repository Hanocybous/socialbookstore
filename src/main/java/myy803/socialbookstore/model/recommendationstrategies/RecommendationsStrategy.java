package myy803.socialbookstore.model.recommendationstrategies;

import myy803.socialbookstore.dto.BookDto;

import java.util.List;


/**
 * Interface for recommendation strategies.
 * <p>
 * This interface defines the contract for recommendation strategies that provide book recommendations
 * based on different criteria.
 * </p>
 */
public interface RecommendationsStrategy {

    /**
     * Recommends books to a user.
     *
     * @param username The username of the user.
     * @return A list of recommended books.
     */
    List<BookDto> recommend(String username);

}
