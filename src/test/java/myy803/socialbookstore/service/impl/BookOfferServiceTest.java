package myy803.socialbookstore.service.impl;

import myy803.socialbookstore.dto.BookDto;
import myy803.socialbookstore.mapper.BookAuthorMapper;
import myy803.socialbookstore.mapper.BookCategoryMapper;
import myy803.socialbookstore.mapper.BookMapper;
import myy803.socialbookstore.mapper.UserProfileMapper;
import myy803.socialbookstore.model.entities.Book;
import myy803.socialbookstore.model.entities.BookCategory;
import myy803.socialbookstore.model.entities.UserProfile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookOfferServiceTest {

    @Mock
    private UserProfileMapper userProfileMapper;

    @Mock
    private BookCategoryMapper bookCategoryMapper;

    @Mock
    private BookAuthorMapper bookAuthorMapper;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookOfferService bookOfferService;

    @Mock
    private UserProfile userProfile;

    @Mock
    private BookDto bookDto;

    @Mock
    private Book book;

    @Before
    public void setUp() {
        when(userProfileMapper.findById("testuser")).thenReturn(Optional.of(userProfile));
        when(bookDto.buildBookOffer(bookAuthorMapper, bookCategoryMapper)).thenReturn(book);
    }

    @After
    public void tearDown() {
        reset(userProfileMapper, bookCategoryMapper, bookAuthorMapper, bookMapper, userProfile, bookDto, book);
    }

    @Test
    public void getUserBookOffers_returnsBookOffersForExistingUser() {
        when(userProfile.buildBookOffersDtos()).thenReturn(Collections.singletonList(bookDto));
        List<BookDto> bookOffers = bookOfferService.getUserBookOffers("testuser");
        assertEquals(1, bookOffers.size());
        assertEquals(bookDto, bookOffers.get(0));
    }

    @Test
    public void getUserBookOffers_returnsNullForNonExistingUser() {
        when(userProfileMapper.findById("nonexistentuser")).thenReturn(Optional.empty());
        List<BookDto> bookOffers = bookOfferService.getUserBookOffers("nonexistentuser");
        assertNull(bookOffers);
    }

    @Test
    public void getAllBookCategories_returnsAllBookCategories() {
        List<BookCategory> categories = Collections.singletonList(new BookCategory());
        when(bookCategoryMapper.findAll()).thenReturn(categories);
        List<BookCategory> result = bookOfferService.getAllBookCategories();
        assertEquals(categories, result);
    }

    @Test
    public void saveBookOffer_savesBookOfferForExistingUser() {
        bookOfferService.saveBookOffer("testuser", bookDto);
        verify(userProfile).addBookOffer(book);
        verify(userProfileMapper).save(userProfile);
    }

    @Test
    public void saveBookOffer_doesNothingForNonExistingUser() {
        when(userProfileMapper.findById("nonexistentuser")).thenReturn(Optional.empty());
        bookOfferService.saveBookOffer("nonexistentuser", bookDto);
        verify(userProfile, never()).addBookOffer(any(Book.class));
        verify(userProfileMapper, never()).save(any(UserProfile.class));
    }

    @Test
    public void deleteBookOffer_deletesBookOfferIfExists() {
        when(bookMapper.findById(1)).thenReturn(Optional.of(book));
        bookOfferService.deleteBookOffer(1);
        verify(bookMapper).delete(book);
    }

    @Test
    public void deleteBookOffer_doesNothingIfBookDoesNotExist() {
        when(bookMapper.findById(1)).thenReturn(Optional.empty());
        bookOfferService.deleteBookOffer(1);
        verify(bookMapper, never()).delete(any(Book.class));
    }
}
