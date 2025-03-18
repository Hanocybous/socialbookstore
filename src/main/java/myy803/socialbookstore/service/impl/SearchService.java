package myy803.socialbookstore.service.impl;

import myy803.socialbookstore.dto.BookDto;
import myy803.socialbookstore.dto.SearchDto;
import myy803.socialbookstore.mapper.BookMapper;
import myy803.socialbookstore.model.searchstrategies.SearchFactory;
import myy803.socialbookstore.model.searchstrategies.SearchStrategy;
import myy803.socialbookstore.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Service implementation for handling book searches.
 * <p>
 * This service provides a method to search for books based on a given strategy.
 * </p>
 *
 * @see ISearchService
 * @see SearchFactory
 * @see SearchStrategy
 * @see BookMapper
 * @see BookDto
 * @see SearchDto
 */
@Service
public class SearchService implements ISearchService {

    @Autowired
    private SearchFactory searchFactory;

    @Autowired
    private BookMapper bookMapper;

    public List<BookDto> searchBooks(SearchDto searchDto) {
        SearchStrategy searchStrategy = searchFactory.create(searchDto.getSelectedStrategy());
        if (searchStrategy == null) {
            return Collections.emptyList();
        }
        return searchStrategy.search(searchDto, bookMapper);
    }
}
