package myy803.socialbookstore.config;

import myy803.socialbookstore.service.impl.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * This class is used to configure the security of the application.
 * It defines the access rules for different paths and the login/logout pages.
 * It also defines the authentication provider and the password encoder.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final CustomSecuritySuccessHandler customSecuritySuccessHandler;

    public WebSecurityConfig(CustomSecuritySuccessHandler customSecuritySuccessHandler) {
        this.customSecuritySuccessHandler = customSecuritySuccessHandler;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * This method is used to configure the security of the application.
     * It defines the access rules for different paths and the login/logout pages.
     * It also defines the authentication provider and the password encoder.
     *
     * @param http the HttpSecurity object to configure the security of the application
     * @return the SecurityFilterChain object that represents the security configuration
     * @throws Exception if an error occurs while configuring the security
     * @see SecurityFilterChain
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers("/", "/login", "/register", "/save").permitAll()
                .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
                .requestMatchers("/user/**").hasAnyAuthority("USER")
                .anyRequest().authenticated()
        );

        http.formLogin(fL -> fL.loginPage("/login")
                .failureUrl("/login?error=true")
                .successHandler(customSecuritySuccessHandler)
                .usernameParameter("username")
                .passwordParameter("password")
        );

        http.logout(logOut -> logOut.logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
        );

        http.authenticationProvider(authenticationProvider());

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("/images/**", "/js/**", "/webjars/**");
    }

}