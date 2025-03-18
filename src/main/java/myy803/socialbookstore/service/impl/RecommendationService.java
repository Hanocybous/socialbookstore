package myy803.socialbookstore.service.impl;

import myy803.socialbookstore.dto.BookDto;
import myy803.socialbookstore.dto.RecommendationsDto;
import myy803.socialbookstore.model.recommendationstrategies.RecommendationsFactory;
import myy803.socialbookstore.model.recommendationstrategies.RecommendationsStrategy;
import myy803.socialbookstore.service.IRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Service implementation for handling book recommendations.
 * <p>
 * This service provides a method to recommend books based on a given strategy.
 * </p>
 *
 * @see IRecommendationService
 * @see RecommendationsFactory
 * @see RecommendationsStrategy
 * @see BookDto
 * @see RecommendationsDto
 */
@Service
public class RecommendationService implements IRecommendationService {

    @Autowired
    private RecommendationsFactory recommendationsFactory;

    public List<BookDto> recommendBooks(String username, RecommendationsDto recomDto) {
        RecommendationsStrategy recommendationsStrategy = recommendationsFactory.create(recomDto.getSelectedStrategy());
        if (recommendationsStrategy == null) {
            return Collections.emptyList();
        }
        return recommendationsStrategy.recommend(username);
    }
}
