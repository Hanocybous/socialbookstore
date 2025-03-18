package myy803.socialbookstore.service;

import myy803.socialbookstore.dto.BookDto;
import myy803.socialbookstore.model.entities.BookCategory;

import java.util.List;

/**
 * Service interface for managing book offers.
 * <p>
 * This service provides methods to get user book offers, retrieve all book categories,
 * save a book offer, and delete a book offer.
 * </p>
 *
 * @see myy803.socialbookstore.service.impl.BookOfferService
 * @see myy803.socialbookstore.model.entities.BookCategory
 * @see myy803.socialbookstore.dto.BookDto
 * @see myy803.socialbookstore.model.entities.Book
 *
 */
public interface IBookOfferService {

    /**
     * @param username username of the user
     * @return list of book offers of the user
     */
    List<BookDto> getUserBookOffers(String username);

    /**
     * @return list of all book categories
     */
    List<BookCategory> getAllBookCategories();

    /**
     * @param username     username of the user
     * @param bookOfferDto book offer to save
     */
    void saveBookOffer(String username, BookDto bookOfferDto);

    /**
     * @param bookId id of the book offer to delete
     */
    void deleteBookOffer(int bookId);
}
