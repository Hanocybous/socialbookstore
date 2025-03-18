package myy803.socialbookstore.service.impl;

import myy803.socialbookstore.model.entities.User;
import myy803.socialbookstore.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service implementation for authentication operations.
 * <p>
 * This service provides methods to check if a user is present and to save a user.
 * </p>
 *
 * @see myy803.socialbookstore.service.IAuthService
 * @see myy803.socialbookstore.model.entities.User
 */
@Service
public class AuthService implements IAuthService {

    private final UserService userService;

    @Autowired
    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public boolean isUserPresent(User user) {
        return userService.isUserPresent(user);
    }

    public void saveUser(User user) {
        userService.saveUser(user);
    }
}
