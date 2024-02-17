package devotion.config.security.filter;

import devotion.config.security.authentication.LoginAuthentication;
import devotion.config.security.manager.LoginAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class LoginAuthenticationFilter extends OncePerRequestFilter {

    private final LoginAuthenticationManager loginAuthenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String credentials = String.valueOf(request.getHeader("Authorization"));

        Authentication loginAuth = new LoginAuthentication(false, credentials, null);
        Authentication authentication = loginAuthenticationManager.authenticate(loginAuth);

        if (authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            doFilter(request, response, filterChain);
        }
    }
}
