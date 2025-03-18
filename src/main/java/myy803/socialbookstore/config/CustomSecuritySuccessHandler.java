package myy803.socialbookstore.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *  Custom success handler to redirect user to different pages based on their role
 *  after successful login.
 *  If user is an admin, redirect to /admin/dashboard
 *  If user is a user, redirect to /user/dashboard
 *  If user is neither, redirect to /login?error=true
 */
@Configuration
public class CustomSecuritySuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    /**
     * Redirects user to different pages based on their role after successful login.
     * @param request the request
     * @param response the response
     * @param authentication the authentication
     * @throws java.io.IOException if an I/O error occurs
     */
    @Override
    protected void handle(
            HttpServletRequest request,
            @NotNull HttpServletResponse response,
            Authentication authentication)
            throws java.io.IOException {
        String targetUrl = determineTargetUrl(authentication);
        if (response.isCommitted()) return;
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    /**
     * Determines the target URL based on the user's role.
     * @param authentication the authentication
     * @return the target URL
     */
    protected String determineTargetUrl(@NotNull Authentication authentication) {
        String url = "/login?error=true";
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<>();

        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }

        if (roles.contains("USER")) {
            url = "/user/dashboard";
        } else if (roles.contains("ADMIN")) {
            url = "/admin/dashboard";
        }

        return url;
    }
}
