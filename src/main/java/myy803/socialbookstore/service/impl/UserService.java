package myy803.socialbookstore.service.impl;

import myy803.socialbookstore.mapper.UserMapper;
import myy803.socialbookstore.model.entities.User;
import myy803.socialbookstore.service.IUserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service implementation for handling user-related operations.
 * <p>
 * This service provides methods to save a user, check if a user is present,
 * load user details by username, and find a user by username.
 * </p>
 *
 * @see IUserService
 * @see UserDetailsService
 * @see User
 * @see UserMapper
 * @see BCryptPasswordEncoder
 */
@Service
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserMapper userMapper;

    @Override
    public void saveUser(@NotNull User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userMapper.save(user);
    }

    @Override
    public boolean isUserPresent(@NotNull User user) {
        return userMapper.findByUsername(user.getUsername()).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMapper.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("USER_NOT_FOUND " + username));
    }

    public User findById(String username) {
        return userMapper.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("USER_NOT_FOUND " + username));
    }
}

