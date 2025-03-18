package myy803.socialbookstore.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import myy803.socialbookstore.dto.BookDto;
import myy803.socialbookstore.dto.UserProfileDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Entity class representing a User Profile.
 * <p>
 * This class is annotated with JPA annotations to map it to a database table.
 * It includes fields for the user's username, full name, age, favorite book authors, favorite book categories,
 * book offers, and requested books.
 * </p>
 * <p>
 * This class provides methods to add favorite book authors and categories, build DTOs for book offers and requests,
 * and retrieve books from favorite categories and authors.
 * </p>
 */
@Getter
@Setter
@Entity
@Table(name = "user_profiles")
@Transactional
public class UserProfile {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "age")
    private int age;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "profiles_authors",
            joinColumns = @JoinColumn(name = "user_profile_id", referencedColumnName = "username"),
            inverseJoinColumns = @JoinColumn(name = "book_author_id", referencedColumnName = "author_id")
    )
    private List<BookAuthor> favouriteBookAuthors;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "profiles_categories",
            joinColumns = @JoinColumn(name = "user_profile_id", referencedColumnName = "username"),
            inverseJoinColumns = @JoinColumn(name = "book_category_id", referencedColumnName = "category_id")
    )
    private List<BookCategory> favouriteBookCategories;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_username")
    private List<Book> bookOffers;

    @ManyToMany(mappedBy = "requestingUsers")
    private List<Book> requestedBooks;

    public UserProfile() {
        favouriteBookAuthors = new ArrayList<>();
        favouriteBookCategories = new ArrayList<>();
        bookOffers = new ArrayList<>();
        requestedBooks = new ArrayList<>();
    }

    public UserProfile(String username,
                       String fullName,
                       int age,
                       List<BookAuthor> favouriteBookAuthors,
                       List<BookCategory> favouriteBookCategories,
                       List<Book> bookOffers) {
        this.username = username;
        this.fullName = fullName;
        this.age = age;
        this.favouriteBookAuthors = favouriteBookAuthors;
        this.favouriteBookCategories = favouriteBookCategories;
        this.bookOffers = bookOffers;
    }

    public void addBookAuthor(BookAuthor bookAuthor) {
        favouriteBookAuthors.add(bookAuthor);
    }

    public void addBookCategory(BookCategory bookCategory) {
        favouriteBookCategories.add(bookCategory);
    }

    public List<BookDto> buildBookOffersDtos() {
        return bookOffers.stream().map(Book::buildDto).toList();
    }

    public List<BookDto> buildBookRequestsDtos() {
        return requestedBooks.stream().map(Book::buildDto).toList();
    }

    public UserProfileDto buildProfileDto() {
        String favoriteAuthors = favouriteBookAuthors
                .stream()
                .map(BookAuthor::getName)
                .collect(Collectors.joining(", "));
        String[] favoriteCategories = favouriteBookCategories
                .stream()
                .map(BookCategory::getName)
                .toArray(String[]::new);
        return new UserProfileDto(username, fullName, age, favoriteAuthors, favoriteCategories);
    }

    public void clear() {
        favouriteBookAuthors.clear();
        favouriteBookCategories.clear();
    }

    public void addBookOffer(Book bookOffer) {
        bookOffers.add(bookOffer);
    }

    public List<BookDto> getBooksOfFavouriteCategories() {
        return favouriteBookCategories.stream()  // get all favourite categories
                .flatMap(category -> category.getBookOffers().stream())  // get all books of favourite categories
                .map(Book::buildDto)  // convert to DTO
                .toList();  // collect to list
    }

    public List<BookDto> getBooksFromFavouriteAuthors() {
        return favouriteBookAuthors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(Book::buildDto)
                .toList();
    }

    public List<BookDto> getTopRatedBooks() {
        return bookOffers.stream()
                .sorted((b1, b2) -> Double.compare(b2.getRating(), b1.getRating()))
                .map(Book::buildDto)
                .toList();
    }

    public List<BookDto> getRequestedBookDtos() {
        return requestedBooks.stream()
                .map(Book::buildDto)
                .toList();
    }
}