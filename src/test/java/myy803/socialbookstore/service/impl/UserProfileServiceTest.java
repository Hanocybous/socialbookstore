package myy803.socialbookstore.service.impl;

import myy803.socialbookstore.dto.UserProfileDto;
import myy803.socialbookstore.mapper.BookAuthorMapper;
import myy803.socialbookstore.mapper.BookCategoryMapper;
import myy803.socialbookstore.mapper.UserProfileMapper;
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
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserProfileServiceTest {

    @Mock
    private UserProfileMapper userProfileMapper;

    @Mock
    private BookCategoryMapper bookCategoryMapper;

    @Mock
    private BookAuthorMapper bookAuthorMapper;

    @Mock
    private UserProfile userProfile;

    @Mock
    private UserProfileDto userProfileDto;

    @InjectMocks
    private UserProfileService userProfileService;

    @Before
    public void setUp() {
        when(userProfileMapper.findById("testuser")).thenReturn(Optional.of(userProfile));
        when(userProfile.buildProfileDto()).thenReturn(userProfileDto);
    }

    @After
    public void tearDown() {
        reset(userProfileMapper, bookCategoryMapper, bookAuthorMapper, userProfile, userProfileDto);
    }

    @Test
    public void getUserProfile_returnsUserProfileForExistingUser() {
        UserProfileDto result = userProfileService.getUserProfile("testuser");
        assertEquals(userProfileDto, result);
    }

    @Test
    public void getUserProfile_returnsNewUserProfileDtoForNonExistingUser() {
        when(userProfileMapper.findById("nonexistentuser")).thenReturn(Optional.empty());
        UserProfileDto result = userProfileService.getUserProfile("nonexistentuser");
        assertNotNull(result);
        assertEquals("nonexistentuser", result.getUsername());
    }

    @Test
    public void getAllBookCategories_returnsAllBookCategories() {
        List<BookCategory> categories = Collections.singletonList(new BookCategory());
        when(bookCategoryMapper.findAll()).thenReturn(categories);
        List<BookCategory> result = userProfileService.getAllBookCategories();
        assertEquals(categories, result);
    }

    @Test
    public void saveUserProfile_savesUserProfileForExistingUser() {
        when(userProfileDto.getUsername()).thenReturn("testuser");
        userProfileService.saveUserProfile(userProfileDto);
        verify(userProfileDto).buildUserProfile(userProfile, bookAuthorMapper, bookCategoryMapper);
        verify(userProfileMapper).save(userProfile);
    }

    @Test
    public void saveUserProfile_createsAndSavesNewUserProfileForNonExistingUser() {
        when(userProfileDto.getUsername()).thenReturn("newuser");
        when(userProfileMapper.findById("newuser")).thenReturn(Optional.empty());
        userProfileService.saveUserProfile(userProfileDto);
        verify(userProfileDto).buildUserProfile(any(UserProfile.class), eq(bookAuthorMapper), eq(bookCategoryMapper));
        verify(userProfileMapper).save(any(UserProfile.class));
    }
}
