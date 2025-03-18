package myy803.socialbookstore.service;

import myy803.socialbookstore.dto.BookDto;
import myy803.socialbookstore.dto.SearchDto;

import java.util.List;

/**
 * Service interface for searching books.
 * <p>
 * This service provides a method to search for books based on the provided search data.
 * </p>
 *
 * @see myy803.socialbookstore.dto.BookDto
 * @see myy803.socialbookstore.dto.SearchDto
 */
public interface ISearchService {

    /**
     * @param searchDto contains the search query
     * @return a list of books that match the search query
     */
    List<BookDto> searchBooks(SearchDto searchDto);
}
