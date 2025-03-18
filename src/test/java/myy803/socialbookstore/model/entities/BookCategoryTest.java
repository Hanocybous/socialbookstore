package myy803.socialbookstore.model.entities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
public class BookCategoryTest {

    private BookCategory bookCategory;

    @Before
    public void setUp() {
        bookCategory = new BookCategory();
        bookCategory.setCategoryId(1);
        bookCategory.setName("Test Category");
    }

    @After
    public void tearDown() {
        bookCategory = null;
    }

    @Test
    public void checkName_returnsTrueForMatchingName() {
        boolean result = bookCategory.checkName("Test Category");
        assertTrue(result);
    }

    @Test
    public void checkName_returnsFalseForNonMatchingName() {
        boolean result = bookCategory.checkName("Non Matching Category");
        assertFalse(result);
    }

    @Test
    public void equals_returnsTrueForSameCategoryId() {
        BookCategory otherCategory = new BookCategory();
        otherCategory.setCategoryId(1);
        boolean result = bookCategory.equals(otherCategory);
        assertTrue(result);
    }

    @Test
    public void equals_returnsFalseForDifferentCategoryId() {
        BookCategory otherCategory = new BookCategory();
        otherCategory.setCategoryId(2);
        boolean result = bookCategory.equals(otherCategory);
        assertFalse(result);
    }

    @Test
    public void hashCode_returnsCorrectHashCode() {
        int expectedHashCode = Objects.hash(1);
        int actualHashCode = bookCategory.hashCode();
        assertEquals(expectedHashCode, actualHashCode);
    }
}
