package myy803.socialbookstore.dto;

import lombok.Getter;
import lombok.Setter;
import myy803.socialbookstore.mapper.BookAuthorMapper;
import myy803.socialbookstore.mapper.BookCategoryMapper;
import myy803.socialbookstore.model.entities.BookAuthor;
import myy803.socialbookstore.model.entities.BookCategory;
import myy803.socialbookstore.model.entities.UserProfile;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Data transfer object for user profile.
 * Used to transfer user profile data between the client and the server.
 * Contains the user's username, full name, age, favourite book authors and categories.
 * Contains methods to build a UserProfile object from the data.
 *
 * @see UserProfile
 * @see BookAuthor
 * @see BookCategory
 * @see BookAuthorMapper
 * @see BookCategoryMapper
 */
@Getter
@Setter
@Component
public class UserProfileDto {

    private String username;
    private String fullName;
    private int age;
    private String favouriteBookAuthors;
    private String[] favouriteBookCategories;

    public UserProfileDto(String username, String fullName, int age, String favouriteBookAuthors,
                          String[] favouriteBookCategories) {
        this.username = username;
        this.fullName = fullName;
        this.age = age;
        this.favouriteBookAuthors = favouriteBookAuthors;
        this.favouriteBookCategories = favouriteBookCategories;
    }

    public UserProfileDto() {
    }

    public UserProfileDto(String username) {
        this.username = username;
        this.fullName = "";
        this.age = 0;
        this.favouriteBookAuthors = "";
        this.favouriteBookCategories = new String[0];
    }

    @Override
    public String toString() {
        return "UserProfileDto [username=" + username + ", fullName=" + fullName + ", age=" + age
                + ", favouriteBookAuthors=" + favouriteBookAuthors + ", favouriteBookCategories="
                + Arrays.toString(favouriteBookCategories) + "]";
    }

    /**
     * Builds a UserProfile object from the data in the UserProfileDto object.
     * The UserProfile object is built using the data in the UserProfileDto object and the data in the BookAuthorMapper and BookCategoryMapper objects.
     *
     * @param userProfile         The UserProfile object to build.
     * @param bookAuthorMapper    The BookAuthorMapper object to use to find the book authors.
     * @param bookCategoriesMapper The BookCategoryMapper object to use to find the book categories.
     */
    public void buildUserProfile(UserProfile userProfile, BookAuthorMapper bookAuthorMapper, BookCategoryMapper bookCategoriesMapper) {
        userProfile.clear();

        userProfile.setUsername(username);
        userProfile.setFullName(fullName);
        userProfile.setAge(age);

        String[] bookAuthorsNames = favouriteBookAuthors.split(",");
        for (String bookAuthorsName : bookAuthorsNames) {
            List<BookAuthor> bookAuthors = bookAuthorMapper.findByName(bookAuthorsName.trim());
            BookAuthor bookAuthor = null;
            if (bookAuthors.isEmpty()) {
                bookAuthor = new BookAuthor();
                bookAuthor.setName(bookAuthorsName.trim());
            } else
                bookAuthor = bookAuthors.get(0);

            userProfile.addBookAuthor(bookAuthor);
        }

        for (String favouriteBookCategory : favouriteBookCategories) {
            List<BookCategory> categories = bookCategoriesMapper.findByName(favouriteBookCategory);
            userProfile.addBookCategory(categories.get(0));
        }
    }

    /**
     * Builds a UserProfile object from the data in the UserProfileDto object.
     * The UserProfile object is built using the data in the UserProfileDto object and the data in the BookCategoryMapper object.
     *
     * @param userProfile         The UserProfile object to build.
     * @param bookCategoryMapper The BookCategoryMapper object to use to find the book categories.
     */
    public void buildUserProfile(@NotNull UserProfile userProfile, BookCategoryMapper bookCategoryMapper) {
        userProfile.clear();

        userProfile.setUsername(username);
        userProfile.setFullName(fullName);
        userProfile.setAge(age);

        for (String favouriteBookCategory : favouriteBookCategories) {
            List<BookCategory> categories = bookCategoryMapper.findByName(favouriteBookCategory);
            userProfile.addBookCategory(categories.get(0));
        }
    }
}
