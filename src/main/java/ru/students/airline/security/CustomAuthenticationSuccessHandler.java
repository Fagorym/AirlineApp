package ru.students.airline.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final String USER_PAGE = "/";
    private final String ADMIN_PAGE = "/admin";

    private final SimpleUrlAuthenticationSuccessHandler userSuccessHandler =
            new SimpleUrlAuthenticationSuccessHandler(USER_PAGE);
    private final SimpleUrlAuthenticationSuccessHandler adminSuccessHandler =
            new SimpleUrlAuthenticationSuccessHandler(ADMIN_PAGE);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (final GrantedAuthority grantedAuthority : authorities) {
            String authorityName = grantedAuthority.getAuthority();
            switch (authorityName) {
                case "ROLE_USER":
                    userSuccessHandler.onAuthenticationSuccess(request, response, authentication);
                    return;
                case "ROLE_ADMIN":
                    adminSuccessHandler.onAuthenticationSuccess(request, response, authentication);
                    return;
            }
        }
        throw new IllegalStateException();
    }
}