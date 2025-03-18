package myy803.socialbookstore.model.entities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
public class BookAuthorTest {

    @Mock
    private Book book;

    @InjectMocks
    private BookAuthor bookAuthor;

    @Before
    public void setUp() {
        bookAuthor = new BookAuthor("Test Author");
        bookAuthor.setAuthorId(1);
        bookAuthor.getBooks().add(book);
    }

    @After
    public void tearDown() {
        bookAuthor = null;
        book = null;
    }

    @Test
    public void getName_returnsCorrectName() {
        assertEquals("Test Author", bookAuthor.getName());
    }

    @Test
    public void getBooks_returnsCorrectBooks() {
        List<Book> books = bookAuthor.getBooks();
        assertEquals(1, books.size());
        assertTrue(books.contains(book));
    }

    @Test
    public void equals_returnsTrueForSameAuthorId() {
        BookAuthor otherAuthor = new BookAuthor("Other Author");
        otherAuthor.setAuthorId(1);
        assertEquals(bookAuthor, otherAuthor);
    }

    @Test
    public void hashCode_returnsCorrectHashCode() {
        assertEquals(Objects.hash(1), bookAuthor.hashCode());
    }
}
