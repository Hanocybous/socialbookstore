package myy803.socialbookstore.service.impl;

import myy803.socialbookstore.dto.BookDto;
import myy803.socialbookstore.dto.UserProfileDto;
import myy803.socialbookstore.mapper.BookMapper;
import myy803.socialbookstore.mapper.UserProfileMapper;
import myy803.socialbookstore.model.entities.Book;
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
public class BookRequestServiceTest {

    @Mock
    private UserProfileMapper userProfileMapper;

    @Mock
    private BookMapper bookMapper;

    @Mock
    private UserProfile userProfile;

    @Mock
    private Book book;

    @Mock
    private BookDto bookDto;

    @Mock
    private UserProfileDto userProfileDto;

    @InjectMocks
    private BookRequestService bookRequestService;

    @Before
    public void setUp() {
        when(userProfileMapper.findById("testuser")).thenReturn(Optional.of(userProfile));
        when(bookMapper.findById(1)).thenReturn(Optional.of(book));
    }

    @After
    public void tearDown() {
        reset(userProfileMapper, bookMapper, userProfile, book, bookDto, userProfileDto);
    }

    @Test
    public void requestBook_addsRequestingUserToBook() {
        bookRequestService.requestBook(1, "testuser");
        verify(book).addRequestingUser(userProfile);
        verify(bookMapper).save(book);
    }

    @Test
    public void requestBook_doesNothingIfBookNotFound() {
        when(bookMapper.findById(1)).thenReturn(Optional.empty());
        bookRequestService.requestBook(1, "testuser");
        verify(book, never()).addRequestingUser(any(UserProfile.class));
        verify(bookMapper, never()).save(any(Book.class));
    }

    @Test
    public void getUserBookRequests_returnsNullForNonExistingUser() {
        when(userProfileMapper.findById("nonexistentuser")).thenReturn(Optional.empty());
        List<BookDto> bookRequests = bookRequestService.getUserBookRequests("nonexistentuser");
        assertNull(bookRequests);
    }

    @Test
    public void getRequestingUsersForBookOffer_returnsRequestingUsersForExistingBook() {
        when(book.getRequestingUserProfileDtos()).thenReturn(Collections.singletonList(userProfileDto));
        List<UserProfileDto> requestingUsers = bookRequestService.getRequestingUsersForBookOffer(1);
        assertEquals(1, requestingUsers.size());
        assertEquals(userProfileDto, requestingUsers.get(0));
    }

    @Test
    public void getRequestingUsersForBookOffer_returnsNullForNonExistingBook() {
        when(bookMapper.findById(1)).thenReturn(Optional.empty());
        List<UserProfileDto> requestingUsers = bookRequestService.getRequestingUsersForBookOffer(1);
        assertNull(requestingUsers);
    }

    @Test
    public void deleteBookRequest_removesRequestingUserFromBook() {
        bookRequestService.deleteBookRequest(1, "testuser");
        verify(book).removeRequestingUser(userProfile);
        verify(bookMapper).save(book);
    }
}
