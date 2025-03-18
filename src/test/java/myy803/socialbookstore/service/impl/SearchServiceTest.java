package myy803.socialbookstore.service.impl;

import myy803.socialbookstore.dto.BookDto;
import myy803.socialbookstore.dto.SearchDto;
import myy803.socialbookstore.mapper.BookMapper;
import myy803.socialbookstore.model.searchstrategies.SearchFactory;
import myy803.socialbookstore.model.searchstrategies.SearchStrategy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SearchServiceTest {

    @Mock
    private SearchFactory searchFactory;

    @Mock
    private BookMapper bookMapper;

    @Mock
    private SearchStrategy searchStrategy;

    @Mock
    private SearchDto searchDto;

    @InjectMocks
    private SearchService searchService;

    @Before
    public void setUp() {
        when(searchDto.getSelectedStrategy()).thenReturn("strategy");
        when(searchFactory.create("strategy")).thenReturn(searchStrategy);
    }

    @After
    public void tearDown() {
        reset(searchFactory, searchStrategy, searchDto);
    }

    @Test
    public void searchBooks_returnsBooksForValidStrategy() {
        when(searchStrategy.search(searchDto, bookMapper)).thenReturn(Collections.singletonList(new BookDto()));

        List<BookDto> books = searchService.searchBooks(searchDto);

        assertEquals(1, books.size());
    }

    @Test
    public void searchBooks_returnsEmptyListForInvalidStrategy() {
        when(searchFactory.create("invalidStrategy")).thenReturn(null);
        when(searchDto.getSelectedStrategy()).thenReturn("invalidStrategy");

        List<BookDto> books = searchService.searchBooks(searchDto);

        assertEquals(0, books.size());
    }
}
