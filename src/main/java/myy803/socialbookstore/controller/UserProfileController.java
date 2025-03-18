package myy803.socialbookstore.controller;

import myy803.socialbookstore.dto.UserProfileDto;
import myy803.socialbookstore.model.entities.BookCategory;
import myy803.socialbookstore.service.impl.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Controller for user profile page. It retrieves the user's profile and displays it in the profile page.
 * It also saves the user's profile when the user submits the form.
 * The user's profile is saved in the database.
 *
 * @see UserProfileService
 * @see UserProfileDto
 * @see BookCategory
 */
@Controller
@RequestMapping("/user")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @RequestMapping("/profile")
    public String retrieveProfile(Model model) {
        String username = getCurrentUsername();
        List<BookCategory> categories = userProfileService.getAllBookCategories();
        model.addAttribute("categories", categories);

        UserProfileDto userProfileDto = userProfileService.getUserProfile(username);
        model.addAttribute("profile", userProfileDto);
        return "user/profile";
    }

    @RequestMapping("/save_profile")
    public String saveProfile(@ModelAttribute("profile") UserProfileDto userProfileDto) {
        userProfileService.saveUserProfile(userProfileDto);
        return "user/dashboard";
    }

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}