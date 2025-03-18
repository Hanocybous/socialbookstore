package myy803.socialbookstore.model.searchstrategies;

import myy803.socialbookstore.dto.BookDto;
import myy803.socialbookstore.dto.SearchDto;
import myy803.socialbookstore.mapper.BookMapper;
import myy803.socialbookstore.model.entities.Book;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Abstract base class for search strategies.
 * <p>
 * This abstract class provides a template for implementing different search strategies
 * that can be used to search for books based on various criteria.
 * </p>
 *
 * @see SearchStrategy
 * @see SearchDto
 * @see Book
 * @see BookDto
 * @see BookMapper
 */
@Transactional
public abstract class AbstractSearchStrategy implements SearchStrategy {

    protected final BookMapper bookMapper;

    @Autowired
    protected AbstractSearchStrategy(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public List<BookDto> search(@NotNull SearchDto searchDto, BookMapper bookMapper) {
        if (searchDto.getTitle() == null) {
            return List.of();
        }

        List<Book> books = makeInitialListOfBooks(searchDto);
        return books.stream()
                .filter(book -> searchDto.getAuthors().isEmpty() || checkIfAuthorsMatch(searchDto, book))
                .map(Book::buildDto)
                .toList();
    }

    protected abstract List<Book> makeInitialListOfBooks(@NotNull SearchDto searchDto);

    protected abstract boolean checkIfAuthorsMatch(@NotNull SearchDto searchDto, @NotNull Book book);
}
