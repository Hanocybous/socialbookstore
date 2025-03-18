package myy803.socialbookstore.service.impl;

import myy803.socialbookstore.dto.BookDto;
import myy803.socialbookstore.dto.RecommendationsDto;
import myy803.socialbookstore.model.recommendationstrategies.RecommendationsFactory;
import myy803.socialbookstore.model.recommendationstrategies.RecommendationsStrategy;

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
public class RecommendationServiceTest {

    @Mock
    private RecommendationsFactory recommendationsFactory;

    @Mock
    private RecommendationsStrategy recommendationsStrategy;

    @InjectMocks
    private RecommendationService recommendationService;

    @Mock
    private RecommendationsDto recommendationsDto;

    @Before
    public void setUp() {
        when(recommendationsDto.getSelectedStrategy()).thenReturn("strategy");
    }

    @After
    public void tearDown() {
        reset(recommendationsFactory, recommendationsStrategy, recommendationsDto);
    }

    @Test
    public void recommendBooks_returnsRecommendedBooksForValidStrategy() {
        when(recommendationsFactory.create("strategy")).thenReturn(recommendationsStrategy);
        when(recommendationsStrategy.recommend("testuser")).thenReturn(Collections.singletonList(new BookDto()));

        List<BookDto> recommendedBooks = recommendationService.recommendBooks("testuser", recommendationsDto);

        assertEquals(1, recommendedBooks.size());
    }

    @Test
    public void recommendBooks_returnsEmptyListForInvalidStrategy() {
        when(recommendationsFactory.create("strategy")).thenReturn(null);

        List<BookDto> recommendedBooks = recommendationService.recommendBooks("testuser", recommendationsDto);

        assertEquals(0, recommendedBooks.size());
    }
}
