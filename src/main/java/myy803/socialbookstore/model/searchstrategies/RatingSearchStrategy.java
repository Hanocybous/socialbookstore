package myy803.socialbookstore.model.searchstrategies;

import myy803.socialbookstore.dto.SearchDto;
import myy803.socialbookstore.mapper.BookMapper;
import myy803.socialbookstore.model.entities.Book;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Search strategy that searches for books with rating greater than a given threshold.
 *
 * @see AbstractSearchStrategy
 * @see SearchDto
 * @see Book
 * @see BookMapper
 */
@Component
@Transactional
public class RatingSearchStrategy extends AbstractSearchStrategy {

    @Autowired
    public RatingSearchStrategy(BookMapper bookMapper) {
        super(bookMapper);
    }

    @Override
    protected List<Book> makeInitialListOfBooks(@NotNull SearchDto searchDto) {
        double ratingThreshold = searchDto.getRating();
        return bookMapper.findByRatingGreaterThan(ratingThreshold);
    }

    @Override
    protected boolean checkIfAuthorsMatch(@NotNull SearchDto searchDto, @NotNull Book book) {
        return book.authorsListIncludes(searchDto.getAuthors());
    }
}
