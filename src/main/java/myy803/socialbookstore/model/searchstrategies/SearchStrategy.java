package myy803.socialbookstore.model.searchstrategies;

import myy803.socialbookstore.dto.BookDto;
import myy803.socialbookstore.dto.SearchDto;
import myy803.socialbookstore.mapper.BookMapper;

import java.util.List;

/**
 * A strategy to search for books based on a search criteria
 *
 * @see SearchDto
 * @see BookDto
 * @see BookMapper
 */
public interface SearchStrategy {


    /**
     * @param searchDto  The search criteria
     * @param bookMapper The mapper to use to map the books to BookDto
     * @return A list of books that match the search criteria
     */
    List<BookDto> search(SearchDto searchDto, BookMapper bookMapper);

}