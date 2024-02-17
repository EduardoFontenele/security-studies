package devotion.config.security.filter;

import devotion.config.security.authentication.HeaderAuthentication;
import devotion.config.security.manager.HeaderAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class HeaderAuthenticationFilter extends OncePerRequestFilter {

    private final HeaderAuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String key = request.getHeader("key");

        Authentication customAuthentication = new HeaderAuthentication(false, key);
        Authentication authenticationObject = authenticationManager.authenticate(customAuthentication);

        if (authenticationObject.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationObject);
            doFilter(request, response, filterChain);
        }

        throw new BadCredentialsException("Not authenticated");
    }
}
