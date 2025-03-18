package myy803.socialbookstore.service.impl;

import myy803.socialbookstore.mapper.UserMapper;
import myy803.socialbookstore.model.entities.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserMapper userMapper;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserService userService;

    @Mock
    private User user;

    @Before
    public void setUp() {
        when(user.getUsername()).thenReturn("testuser");
        when(user.getPassword()).thenReturn("password");
    }

    @After
    public void tearDown() {
        reset(user, userMapper, bCryptPasswordEncoder);
    }

    @Test
    public void saveUser_encodesPasswordAndSavesUser() {
        when(bCryptPasswordEncoder.encode("password")).thenReturn("encodedPassword");
        userService.saveUser(user);
        verify(user).setPassword("encodedPassword");
        verify(userMapper).save(user);
    }

    @Test
    public void isUserPresent_returnsTrueIfUserExists() {
        when(userMapper.findByUsername("testuser")).thenReturn(Optional.of(user));
        assertTrue(userService.isUserPresent(user));
    }

    @Test
    public void isUserPresent_returnsFalseIfUserDoesNotExist() {
        when(userMapper.findByUsername("testuser")).thenReturn(Optional.empty());
        assertFalse(userService.isUserPresent(user));
    }

    @Test
    public void loadUserByUsername_returnsUserDetailsIfUserExists() {
        when(userMapper.findByUsername("testuser")).thenReturn(Optional.of(user));
        assertEquals(user, userService.loadUserByUsername("testuser"));
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsername_throwsExceptionIfUserDoesNotExist() {
        when(userMapper.findByUsername("testuser")).thenReturn(Optional.empty());
        userService.loadUserByUsername("testuser");
    }

    @Test
    public void findById_returnsUserIfUserExists() {
        when(userMapper.findByUsername("testuser")).thenReturn(Optional.of(user));
        assertEquals(user, userService.findById("testuser"));
    }

    @Test(expected = UsernameNotFoundException.class)
    public void findById_throwsExceptionIfUserDoesNotExist() {
        when(userMapper.findByUsername("testuser")).thenReturn(Optional.empty());
        userService.findById("testuser");
    }
}
