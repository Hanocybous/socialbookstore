package myy803.socialbookstore.service;

import myy803.socialbookstore.dto.BookDto;
import myy803.socialbookstore.dto.UserProfileDto;

import java.util.List;

/**
 * Service interface for handling book requests.
 * <p>
 * This service provides methods to request a book, get user book requests,
 * get requesting users for a book offer, accept a book request, and delete a book request.
 * </p>
 *
 * @see myy803.socialbookstore.dto.BookDto
 * @see myy803.socialbookstore.dto.UserProfileDto
 */
public interface IBookRequestService {

    /**
     * @param bookId   The id of the book to request
     * @param username The username of the user requesting the book
     */
    void requestBook(int bookId, String username);

    /**
     * @param username The username of the user requesting the book
     * @return A list of books that the user has requested
     */
    List<BookDto> getUserBookRequests(String username);

    /**
     * @param bookId The id of the book to get the requesting users for
     * @return A list of users that have requested the book
     */
    List<UserProfileDto> getRequestingUsersForBookOffer(int bookId);

    /**
     * @param username The username of the user requesting the book
     * @param bookId   The id of the book to accept the request for
     */
    void acceptRequest(String username, int bookId);

    /**
     * @param username The username of the user requesting the book
     * @param bookId   The id of the book to delete the request for
     */
    void deleteBookRequest(int bookId, String username);
}
