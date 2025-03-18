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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
public class UserProfileTest {

    @Mock
    private BookAuthor bookAuthor;

    @Mock
    private BookCategory bookCategory;

    @Mock
    private Book book;

    @InjectMocks
    private UserProfile userProfile;

    @Before
    public void setUp() {
        userProfile = new UserProfile("testuser", "Test User", 30, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        userProfile.addBookAuthor(bookAuthor);
        userProfile.addBookCategory(bookCategory);
        userProfile.addBookOffer(book);
    }

    @After
    public void tearDown() {
        reset(bookAuthor, bookCategory, book);
    }

    @Test
    public void buildProfileDto_returnsCorrectUserProfileDto() {
        when(bookAuthor.getName()).thenReturn("Test Author");
        when(bookCategory.getName()).thenReturn("Test Category");

        UserProfileDto userProfileDto = userProfile.buildProfileDto();

        assertEquals("testuser", userProfileDto.getUsername());
        assertEquals("Test User", userProfileDto.getFullName());
        assertEquals(30, userProfileDto.getAge());
        assertEquals("Test Author", userProfileDto.getFavouriteBookAuthors());
        assertEquals(1, userProfileDto.getFavouriteBookCategories().length);
        assertEquals("Test Category", userProfileDto.getFavouriteBookCategories()[0]);
    }

    @Test
    public void buildBookOffersDtos_returnsCorrectBookDtos() {
        when(book.buildDto()).thenReturn(new BookDto());

        List<BookDto> bookDtos = userProfile.buildBookOffersDtos();

        assertEquals(1, bookDtos.size());
    }

    @Test
    public void buildBookRequestsDtos_returnsCorrectBookDtos() {
        if (userProfile.getRequestedBooks() == null) {
            userProfile.setRequestedBooks(new ArrayList<>());
        }
        userProfile.getRequestedBooks().add(book);
        when(book.buildDto()).thenReturn(new BookDto());

        List<BookDto> bookDtos = userProfile.buildBookRequestsDtos();

        assertEquals(1, bookDtos.size());
    }

    @Test
    public void clear_clearsFavouriteBookAuthorsAndCategories() {
        userProfile.clear();

        assertTrue(userProfile.getFavouriteBookAuthors().isEmpty());
        assertTrue(userProfile.getFavouriteBookCategories().isEmpty());
    }

    @Test
    public void getBooksOfFavouriteCategories_returnsCorrectBookDtos() {
        when(bookCategory.getBookOffers()).thenReturn(List.of(book));
        when(book.buildDto()).thenReturn(new BookDto());

        List<BookDto> bookDtos = userProfile.getBooksOfFavouriteCategories();

        assertEquals(1, bookDtos.size());
    }

    @Test
    public void getBooksFromFavouriteAuthors_returnsCorrectBookDtos() {
        when(bookAuthor.getBooks()).thenReturn(List.of(book));
        when(book.buildDto()).thenReturn(new BookDto());

        List<BookDto> bookDtos = userProfile.getBooksFromFavouriteAuthors();

        assertEquals(1, bookDtos.size());
    }
}
