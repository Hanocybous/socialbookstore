package myy803.socialbookstore.service.impl;

import myy803.socialbookstore.dto.BookDto;
import myy803.socialbookstore.dto.UserProfileDto;
import myy803.socialbookstore.mapper.BookMapper;
import myy803.socialbookstore.mapper.UserProfileMapper;
import myy803.socialbookstore.model.entities.Book;
import myy803.socialbookstore.model.entities.UserProfile;
import myy803.socialbookstore.service.IBookRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service implementation for handling book requests.
 * <p>
 * This service provides methods to request a book, get user book requests,
 * get requesting users for a book offer, accept a book request, and delete a book request.
 * </p>
 *
 * @see IBookRequestService
 * @see UserProfileMapper
 * @see BookMapper
 * @see UserProfile
 * @see Book
 * @see BookDto
 * @see UserProfileDto
 */
@Service
public class BookRequestService implements IBookRequestService {

    private final UserProfileMapper userProfileMapper;
    private final BookMapper bookMapper;

    @Autowired
    public BookRequestService(UserProfileMapper userProfileMapper, BookMapper bookMapper) {
        this.userProfileMapper = userProfileMapper;
        this.bookMapper = bookMapper;
    }

    public void requestBook(int bookId, String username) {
        Optional<Book> requestedBook = bookMapper.findById(bookId);
        Optional<UserProfile> userProfile = userProfileMapper.findById(username);
        requestedBook.ifPresent(book -> userProfile.ifPresent(book::addRequestingUser));
        requestedBook.ifPresent(bookMapper::save);
    }

    public List<BookDto> getUserBookRequests(String username) {
        return userProfileMapper.findById(username)
                .map(UserProfile::getRequestedBookDtos)
                .orElse(null);
    }

    public List<UserProfileDto> getRequestingUsersForBookOffer(int bookId) {
        return bookMapper.findById(bookId)
                .map(Book::getRequestingUserProfileDtos)
                .orElse(null);
    }

    public void acceptRequest(String username, int bookId) {
        bookMapper.findById(bookId).ifPresent(book -> {
            userProfileMapper.findById(username).ifPresent(book::acceptRequest);
            bookMapper.save(book);
        });
        // Clear all other requests for the book
        bookMapper.findById(bookId).ifPresent(book -> book.getRequestingUsers().clear());
    }

    public void deleteBookRequest(int bookId, String username) {
        bookMapper.findById(bookId).ifPresent(book -> {
            userProfileMapper.findById(username).ifPresent(book::removeRequestingUser);
            bookMapper.save(book);
        });
    }

    public void rejectRequest(String username, int bookId) {
        bookMapper.findById(bookId).ifPresent(book -> {
            userProfileMapper.findById(username).ifPresent(book::rejectRequest);
            bookMapper.save(book);
        });
    }
}
