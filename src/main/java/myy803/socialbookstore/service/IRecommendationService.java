package myy803.socialbookstore.service;

import myy803.socialbookstore.dto.BookDto;
import myy803.socialbookstore.dto.RecommendationsDto;

import java.util.List;

/**
 * Service interface for providing book recommendations.
 * <p>
 * This service provides a method to recommend books to a user based on the provided recommendations data.
 * </p>
 *
 * @see myy803.socialbookstore.dto.BookDto
 * @see myy803.socialbookstore.dto.RecommendationsDto
 */
public interface IRecommendationService {

    /**
     * @param username the username of the user
     * @param recomDto the recommendations dto
     * @return a list of recommended books
     */
    List<BookDto> recommendBooks(String username, RecommendationsDto recomDto);
}
