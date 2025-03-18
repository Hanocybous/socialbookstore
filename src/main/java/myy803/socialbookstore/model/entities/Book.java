package myy803.socialbookstore.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import myy803.socialbookstore.dto.BookDto;
import myy803.socialbookstore.dto.UserProfileDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Reference;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Entity class representing a Book.
 * <p>
 * This class is annotated with JPA annotations to map it to a database table.
 * It includes fields for the book's offer ID, title, rating, category, authors, and requesting users.
 * </p>
 *
 * @see BookCategory
 * @see BookAuthor
 * @see UserProfile
 * @see BookDto
 * @see UserProfileDto
 * @see Entity
 */
@Setter
@Getter
@Entity
@Table(name = "books")
@Transactional
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offer_id")
    private int offerId;

    @Column(name = "title")
    private String title;

    @Column(name = "rating")
    private double rating;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "category_id")
    private BookCategory category;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "book_offer_id", referencedColumnName = "offer_id"),
            inverseJoinColumns = @JoinColumn(name = "book_author_id", referencedColumnName = "author_id")
    )
    private List<BookAuthor> bookAuthors = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "books_requesting_users",
            joinColumns = @JoinColumn(name = "book_offer_id", referencedColumnName = "offer_id"),
            inverseJoinColumns = @JoinColumn(name = "requesting_user_id", referencedColumnName = "username")
    )
    private List<UserProfile> requestingUsers = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "accepted_user_id", referencedColumnName = "username")
    @Reference
    private UserProfile acceptedUser;

    public Book() {
    }

    public Book(String title) {
        this.title = title;
    }

    public BookDto buildDto() {
        return new BookDto(offerId, title, buildAuthorsList(), category.getName());
    }

    public void addAuthor(BookAuthor bookAuthor) {
        bookAuthors.add(bookAuthor);
    }

    public boolean belongsTo(String categoryName) {
        return category.checkName(categoryName);
    }

    public boolean writtenBy(String authors) {
        return buildAuthorsList().equals(authors);
    }

    /**
     * Builds a string containing the names of the authors of the book.
     *
     * @return a string containing the names of the authors of the book
     */
    @NotNull
    private String buildAuthorsList() {
        return bookAuthors.stream()
                .map(BookAuthor::getName)
                .collect(Collectors.joining(", "));
    }

    /**
     * Checks if the list of authors of the book includes the given authors.
     *
     * @param authors a string containing the names of the authors to check
     * @return true if the list of authors of the book includes the given authors, false otherwise
     */
    public boolean authorsListIncludes(@NotNull String authors) {
        List<String> requiredAuthors = Arrays.stream(authors.split(","))
                .map(String::trim)
                .toList();
        return new HashSet<>(bookAuthors.stream()
                .map(BookAuthor::getName)
                .toList())
                .containsAll(requiredAuthors);
    }

    public void addRequestingUser(UserProfile userProfile) {
        requestingUsers.add(userProfile);
    }

    public List<UserProfileDto> getRequestingUserProfileDtos() {
        return requestingUsers.stream()
                .map(UserProfile::buildProfileDto)
                .toList();
    }

    public void setAcceptedUser(UserProfile userProfile) {
        requestingUsers.clear();
        requestingUsers.add(userProfile);
    }

    public void removeRequestingUser(UserProfile userProfile) {
        requestingUsers.remove(userProfile);
    }

    public void acceptRequest(UserProfile userProfile) {
        setAcceptedUser(userProfile);
    }

    public void rejectRequest(UserProfile userProfile) {
        removeRequestingUser(userProfile);
    }
}
