package myy803.socialbookstore.model.searchstrategies;

import myy803.socialbookstore.dto.SearchDto;
import myy803.socialbookstore.mapper.BookMapper;
import myy803.socialbookstore.model.entities.Book;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This class is a concrete implementation of the AbstractSearchStrategy class.
 * It is used to search for books that match the search criteria exactly.
 * It is used when the user wants to search for books that have the exact title and authors that the user has entered.
 *
 * @see AbstractSearchStrategy
 * @see SearchDto
 * @see Book
 * @see BookMapper
 */
@Component
public class ExactSearchStrategy extends AbstractSearchStrategy {

    @Autowired
    public ExactSearchStrategy(BookMapper bookMapper) {
        super(bookMapper);
    }

    @Override
    protected List<Book> makeInitialListOfBooks(@NotNull SearchDto searchDto) {
        return bookMapper.findByTitle(searchDto.getTitle());
    }

    @Override
    protected boolean checkIfAuthorsMatch(@NotNull SearchDto searchDto, @NotNull Book book) {
        return book.writtenBy(searchDto.getAuthors());
    }
}
