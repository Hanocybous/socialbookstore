package myy803.socialbookstore.model.entities;

import myy803.socialbookstore.dto.BookDto;
import myy803.socialbookstore.dto.UserProfileDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
public class BookTest {

    @Mock
    private BookCategory bookCategory;

    @Mock
    private BookAuthor bookAuthor;

    @Mock
    private UserProfile userProfile;

    @InjectMocks
    private Book book;

    @Before
    public void setUp() {
        book = new Book("Test Title");
        book.setCategory(bookCategory);
        book.addAuthor(bookAuthor);
        book.addRequestingUser(userProfile);
    }

    @After
    public void tearDown() {
        reset(bookCategory, bookAuthor, userProfile);
    }

    @Test
    public void buildDto_returnsCorrectBookDto() {
        when(bookCategory.getName()).thenReturn("Test Category");
        when(bookAuthor.getName()).thenReturn("Test Author");

        BookDto bookDto = book.buildDto();

        assertEquals("Test Title", bookDto.getTitle());
        assertEquals("Test Author", bookDto.getAuthors());
        assertEquals("Test Category", bookDto.getCategory());
    }

    @Test
    public void belongsTo_returnsTrueForMatchingCategory() {
        when(bookCategory.checkName("Test Category")).thenReturn(true);

        boolean result = book.belongsTo("Test Category");

        assertTrue(result);
    }

    @Test
    public void writtenBy_returnsTrueForMatchingAuthors() {
        when(bookAuthor.getName()).thenReturn("Test Author");

        boolean result = book.writtenBy("Test Author");

        assertTrue(result);
    }

    @Test
    public void authorsListIncludes_returnsTrueWhenAuthorsMatch() {
        when(bookAuthor.getName()).thenReturn("Test Author");

        boolean result = book.authorsListIncludes("Test Author");

        assertTrue(result);
    }

    @Test
    public void getRequestingUserProfileDtos_returnsCorrectUserProfileDtos() {
        when(userProfile.buildProfileDto()).thenReturn(new UserProfileDto());

        List<UserProfileDto> userProfileDtos = book.getRequestingUserProfileDtos();

        assertEquals(1, userProfileDtos.size());
    }

    @Test
    public void addRequestingUser_addsUserToRequestingUsers() {
        UserProfile newUserProfile = new UserProfile();
        book.addRequestingUser(newUserProfile);

        assertEquals(2, book.getRequestingUsers().size());
    }

    @Test
    public void removeRequestingUser_removesUserFromRequestingUsers() {
        book.removeRequestingUser(userProfile);

        assertEquals(0, book.getRequestingUsers().size());
    }
}