package myy803.socialbookstore.controller;

import myy803.socialbookstore.model.entities.User;
import myy803.socialbookstore.service.impl.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for authentication. Handles login and registration.
 * <p>
 *     The login page is mapped to "/login".
 *     The registration page is mapped to "/register".
 *     The registration form is mapped to "/save".
 *     The login page is mapped to "/login".
 * </p>
 *
 * @see AuthService
 * @see User
 * @see Model
 * @see Controller
 */
@Controller
public class AuthController {

    private static final String LOGIN = "auth/login";
    private static final String REGISTER = "auth/register";

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Displays the login page.
     *
     * @return the login page
     */
    @RequestMapping("/login")
    public String login() {
        return LOGIN;
    }

    /**
     * Displays the registration page.
     *
     * @param model the model to add the user
     * @return the registration page
     */
    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return REGISTER;
    }

    /**
     * Registers a user.
     * <p>
     *     If the user is already registered, a message is displayed.
     *     If the user is successfully registered, a message is displayed.
     * </p>
     *
     * @param user the user to register
     * @param model the model to add the success message
     * @return the login page
     */
    @RequestMapping("/save")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        if (authService.isUserPresent(user)) {
            model.addAttribute("successMessage", "User already registered!");
            return LOGIN;
        }

        authService.saveUser(user);
        model.addAttribute("successMessage", "User registered successfully!");
        return LOGIN;
    }
}
