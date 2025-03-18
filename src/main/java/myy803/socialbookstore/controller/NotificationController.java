package myy803.socialbookstore.controller;

import myy803.socialbookstore.service.impl.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Controller for notifications. It handles the requests for the notifications.
 * It uses the NotificationService to get the notifications for the current user.
 * It also uses the SecurityContextHolder to get the current user.
 * It returns the notifications list page.
 * It also returns the notification count.
 *
 * @see NotificationService
 * @see SecurityContextHolder
 * @see Authentication
 * @see Model
 * @see Controller
 */
@Controller
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    /**
     * Returns the notification count.
     * It uses the NotificationService to get the notification count for the current user.
     * It also uses the SecurityContextHolder to get the current user.
     * It returns the notifications list page.
     * It also returns the notification count.
     *
     * @param model the model to add the count to
     * @return the notifications list page
     */
    @GetMapping("/count")
    public String getNotificationCount(Model model) {
        String currentUsername = getCurrentUsername();
        int count = notificationService.getNotificationCountForUser(currentUsername);
        model.addAttribute("count", count);
        return "notifications/list";
    }

    /**
     * Returns the notifications list page.
     * It uses the NotificationService to get the notifications for the current user.
     * It also uses the SecurityContextHolder to get the current user.
     * It returns the notifications list page.
     * It also returns the notification count.
     *
     * @param model the model to add the notifications and the count to
     * @return the notifications list page
     */
    @GetMapping("/list.html")
    public String listNotifications(Model model) {
        String currentUsername = getCurrentUsername();
        List<String> notifications = notificationService.getNotificationsForUser(currentUsername);
        int count = notifications.size();
        model.addAttribute("notifications", notifications);
        model.addAttribute("count", count);
        return "notifications/list";
    }

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}