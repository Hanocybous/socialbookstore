package myy803.socialbookstore.model.entities;

import myy803.socialbookstore.model.enums.Role;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
public class UserTest {

    private User user;

    @Before
    public void setUp() {
        user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        user.setRole(Role.USER);
    }

    @After
    public void tearDown() {
        user = null;
    }

    @Test
    public void getAuthorities_returnsCorrectAuthorities() {
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        assertEquals(1, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority("USER")));
    }

    @Test
    public void isAccountNonExpired_returnsTrue() {
        assertTrue(user.isAccountNonExpired());
    }

    @Test
    public void isAccountNonLocked_returnsTrue() {
        assertTrue(user.isAccountNonLocked());
    }

    @Test
    public void isCredentialsNonExpired_returnsTrue() {
        assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    public void isEnabled_returnsTrue() {
        assertTrue(user.isEnabled());
    }

    @Test
    public void getPassword_returnsCorrectPassword() {
        assertEquals("testpassword", user.getPassword());
    }

    @Test
    public void getUsername_returnsCorrectUsername() {
        assertEquals("testuser", user.getUsername());
    }
}
