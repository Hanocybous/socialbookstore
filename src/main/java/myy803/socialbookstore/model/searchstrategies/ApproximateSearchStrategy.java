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
 * This class is a concrete implementation of the AbstractSearchStrategy class.
 * It is used to search for books in the database that have a title that contains the title provided in the SearchDto.
 * It is used when the user wants to search for books with a title that is similar to the title provided in the SearchDto.
 *
 * @see AbstractSearchStrategy
 * @see SearchDto
 * @see Book
 * @see BookMapper
 */
@Component
@Transactional
public class ApproximateSearchStrategy extends AbstractSearchStrategy {

    @Autowired
    public ApproximateSearchStrategy(BookMapper bookMapper) {
        super(bookMapper);
    }

    @Override
    protected List<Book> makeInitialListOfBooks(@NotNull SearchDto searchDto) {
        return bookMapper.findByTitleContaining(searchDto.getTitle());
    }

    @Override
    protected boolean checkIfAuthorsMatch(@NotNull SearchDto searchDto, @NotNull Book book) {
        return book.authorsListIncludes(searchDto.getAuthors());
    }

}