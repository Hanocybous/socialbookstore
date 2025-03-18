package myy803.socialbookstore.controller;

import myy803.socialbookstore.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for user related pages.
 * <p>
 *     This controller is responsible for handling requests related to user pages.
 *     It is responsible for handling requests related to user dashboard.
 * </p>
 *
 * @see UserService
 * @see Controller
 * @see RequestMapping
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/dashboard")
    public String getUserMainMenu() {
        return "user/dashboard";
    }
}
