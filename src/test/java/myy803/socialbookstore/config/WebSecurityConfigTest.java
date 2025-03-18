package myy803.socialbookstore.config;

import myy803.socialbookstore.service.impl.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WebSecurityConfigTest {

    @Mock
    private CustomSecuritySuccessHandler customSecuritySuccessHandler;

    @Mock
    private AuthenticationConfiguration authenticationConfiguration;

    @InjectMocks
    private WebSecurityConfig webSecurityConfig;

    @Before
    public void setUp() {
        webSecurityConfig = new WebSecurityConfig(customSecuritySuccessHandler);
    }

    @Test
    public void userDetailsService_returnsUserServiceInstance() {
        UserDetailsService userDetailsService = webSecurityConfig.userDetailsService();
        assertNotNull(userDetailsService);
        assertEquals(UserService.class, userDetailsService.getClass());
    }

    @Test
    public void passwordEncoder_returnsBCryptPasswordEncoderInstance() {
        BCryptPasswordEncoder passwordEncoder = webSecurityConfig.passwordEncoder();
        assertNotNull(passwordEncoder);
        assertEquals(BCryptPasswordEncoder.class, passwordEncoder.getClass());
    }

    @Test
    public void authenticationManager_returnsAuthenticationManagerInstance() throws Exception {
        when(authenticationConfiguration.getAuthenticationManager()).thenReturn(mock(AuthenticationManager.class));
        AuthenticationManager authenticationManager = webSecurityConfig.authenticationManager(authenticationConfiguration);
        assertNotNull(authenticationManager);
    }

    @Test
    public void authenticationProvider_returnsDaoAuthenticationProviderInstance() {
        DaoAuthenticationProvider authenticationProvider = webSecurityConfig.authenticationProvider();
        assertNotNull(authenticationProvider);
        assertEquals(DaoAuthenticationProvider.class, authenticationProvider.getClass());
    }

    @Test
    public void webSecurityCustomizer_returnsWebSecurityCustomizerInstance() {
        WebSecurityCustomizer webSecurityCustomizer = webSecurityConfig.webSecurityCustomizer();
        assertNotNull(webSecurityCustomizer);
    }
}
