// SearchDto.java
package myy803.socialbookstore.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

/**
 * Data transfer object for search.
 * Contains search parameters.
 */
@Getter
@Setter
public class SearchDto {

    private String title;
    private String authors;
    private List<String> searchStrategies;
    private String selectedStrategy;
    private double rating;

    public SearchDto() {
        searchStrategies = Arrays.asList(
                "Exact",
                "Approximate",
                "Rating"
        );
    }
}
