package myy803.socialbookstore.service.impl;

import myy803.socialbookstore.dto.BookDto;
import myy803.socialbookstore.mapper.BookAuthorMapper;
import myy803.socialbookstore.mapper.BookCategoryMapper;
import myy803.socialbookstore.mapper.BookMapper;
import myy803.socialbookstore.mapper.UserProfileMapper;
import myy803.socialbookstore.model.entities.Book;
import myy803.socialbookstore.model.entities.BookCategory;
import myy803.socialbookstore.model.entities.UserProfile;
import myy803.socialbookstore.service.IBookOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service implementation for handling book offers.
 * <p>
 * This service provides methods to get user book offers, get all book categories,
 * save a book offer, and delete a book offer.
 * </p>
 *
 * @see IBookOfferService
 * @see UserProfileMapper
 * @see BookCategoryMapper
 * @see BookAuthorMapper
 * @see BookMapper
 * @see UserProfile
 * @see BookCategory
 * @see Book
 * @see BookDto
 */
@Service
public class BookOfferService implements IBookOfferService {

    private final UserProfileMapper userProfileMapper;
    private final BookCategoryMapper bookCategoryMapper;
    private final BookAuthorMapper bookAuthorMapper;
    private final BookMapper bookMapper;

    @Autowired
    public BookOfferService(UserProfileMapper userProfileMapper,
                            BookCategoryMapper bookCategoryMapper,
                            BookAuthorMapper bookAuthorMapper,
                            BookMapper bookMapper) {
        this.userProfileMapper = userProfileMapper;
        this.bookCategoryMapper = bookCategoryMapper;
        this.bookAuthorMapper = bookAuthorMapper;
        this.bookMapper = bookMapper;
    }

    public List<BookDto> getUserBookOffers(String username) {
        Optional<UserProfile> optUserProfile = userProfileMapper.findById(username);
        return optUserProfile.map(UserProfile::buildBookOffersDtos).orElse(null);
    }

    public List<BookCategory> getAllBookCategories() {
        return bookCategoryMapper.findAll();
    }

    public void saveBookOffer(String username, BookDto bookOfferDto) {
        Optional<UserProfile> optUserProfile = userProfileMapper.findById(username);
        optUserProfile.ifPresent(userProfile -> {
            userProfile.addBookOffer(bookOfferDto.buildBookOffer(bookAuthorMapper, bookCategoryMapper));
            userProfileMapper.save(userProfile);
        });
    }

    public void deleteBookOffer(int bookId) {
        bookMapper.findById(bookId).ifPresent(bookMapper::delete);
    }

    public Book findBookOfferById(String bookOfferId) {
        return bookMapper.findById(Integer.parseInt(bookOfferId)).orElse(null);
    }
}