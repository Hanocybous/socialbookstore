package myy803.socialbookstore.controller;

import myy803.socialbookstore.dto.BookDto;
import myy803.socialbookstore.dto.UserProfileDto;
import myy803.socialbookstore.service.impl.BookRequestService;
import myy803.socialbookstore.service.impl.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Controller for handling book requests.
 *
 * The user can request a book, view their requests, view users requesting a book offer, accept or reject requests, and delete requests.
 *
 * @see BookRequestService
 * @see NotificationService
 * @see BookDto
 * @see UserProfileDto
 */
@Controller
@RequestMapping("/user")
@Transactional
public class BookRequestController {

    private static final String DASHBOARD = "/user/dashboard";
    private static final String REQUESTING_USERS = "/user/requesting_users";

    private final BookRequestService bookRequestService;
    private final NotificationService notificationService;

    public BookRequestController(BookRequestService bookRequestService, NotificationService notificationService) {
        this.bookRequestService = bookRequestService;
        this.notificationService = notificationService;
    }

    @RequestMapping("/request_book")
    public String request(@RequestParam("selected_book_id") int bookId) {
        String username = getCurrentUsername();
        bookRequestService.requestBook(bookId, username);
        return DASHBOARD;
    }

    @RequestMapping("/requests")
    public String showUserBookRequests(Model model) {
        String username = getCurrentUsername();
        List<BookDto> userBookRequests = bookRequestService.getUserBookRequests(username);
        model.addAttribute("user_book_requests", userBookRequests);
        return REQUESTING_USERS;
    }

    @RequestMapping("/book_requesting_users")
    public String showRequestingUsersForBookOffer(@RequestParam("selected_offer_id") int bookId, Model model) {
        List<UserProfileDto> requestingUsers = bookRequestService.getRequestingUsersForBookOffer(bookId);
        model.addAttribute("requesting_users", requestingUsers);
        model.addAttribute("book_id", bookId);
        return REQUESTING_USERS;
    }

    @RequestMapping("/accept_request")
    public String acceptBookRequest(@RequestParam("selected_user") String username, @RequestParam("book_id") int bookId) {
        bookRequestService.acceptRequest(username, bookId);
        String message = "Your request for book ID " + bookId + " has been accepted.";
        notificationService.createNotification(username, message);
        return DASHBOARD;
    }

    @RequestMapping("/delete_book_request")
    public String deleteBookRequest(@RequestParam("selected_request_id") int bookId) {
        String username = getCurrentUsername();
        bookRequestService.deleteBookRequest(bookId, username);
        return DASHBOARD;
    }

    @RequestMapping("/reject_request")
    public String rejectBookRequest(@RequestParam("selected_user") String username, @RequestParam("book_id") int bookId) {
        bookRequestService.rejectRequest(username, bookId);
        return DASHBOARD;
    }

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

}
