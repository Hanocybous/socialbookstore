// RecommendationsDto.java
package myy803.socialbookstore.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

/**
 * Data transfer object for recommendations.
 * Contains a list of recommendation strategies and the selected strategy.
 * Used to pass data between the controller and the view.
 *
 * @see myy803.socialbookstore.service.impl.RecommendationService
 */
@Getter
@Setter
public class RecommendationsDto {
    private List<String> recommendationStrategies;
    private String selectedStrategy;

    public RecommendationsDto() {
        recommendationStrategies = Arrays.asList(
                "Favourite Categories",
                "Favourite Authors",
                "Book Ratings",
                "Both"
        );
    }
}
