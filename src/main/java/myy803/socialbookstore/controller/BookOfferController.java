package myy803.socialbookstore.controller;

import myy803.socialbookstore.dto.BookDto;
import myy803.socialbookstore.model.entities.BookCategory;
import myy803.socialbookstore.service.impl.BookOfferService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Controller for book offers. It handles requests for listing, adding and deleting book offers.
 * It also provides a form for adding a new book offer.
 * <p>
 *     This controller is used by logged in users.
 *     It is used to list, add and delete book offers.
 *     It also provides a form for adding a new book offer.
 *     It is used by the user to manage his book offers.
 * </p>
 *
 * @see BookOfferService
 * @see BookDto
 * @see BookCategory
 * @see Model
 * @see Controller
 * @see RequestMapping
 */
@Controller
@RequestMapping("/user")
@Transactional
public class BookOfferController {

    private final BookOfferService bookOfferService;

    @Autowired
    public BookOfferController(BookOfferService bookOfferService) {
        this.bookOfferService = bookOfferService;
    }

    @RequestMapping("/offers")
    public String listBookOffers(Model model) {
        String username = getCurrentUsername();
        List<BookDto> bookOffersDtos = bookOfferService.getUserBookOffers(username);
        model.addAttribute("offers", bookOffersDtos);
        return "user/offers";
    }

    @RequestMapping("/show_offer_form")
    public String showOfferForm(Model model) {
        List<BookCategory> categories = bookOfferService.getAllBookCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("offer", new BookDto());
        return "user/offer-form";
    }

    @RequestMapping("/save_offer")
    public String saveOffer(@ModelAttribute("offer") BookDto bookOfferDto) {
        String username = getCurrentUsername();
        bookOfferService.saveBookOffer(username, bookOfferDto);
        return "redirect:/user/offers";
    }

    @RequestMapping("/delete_book_offer")
    public String deleteBookOffer(@RequestParam("selected_offer_id") int bookId) {
        bookOfferService.deleteBookOffer(bookId);
        return "redirect:/user/dashboard";
    }

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public String listBookOffers(String username, @NotNull Model model) {
        List<BookDto> bookOffersDtos = bookOfferService.getUserBookOffers(username);
        model.addAttribute("offers", bookOffersDtos);
        return "user/offers";
    }

    public String saveOffer(String username, BookDto bookOfferDto) {
        bookOfferService.saveBookOffer(username, bookOfferDto);
        return "redirect:/user/offers";
    }
}
