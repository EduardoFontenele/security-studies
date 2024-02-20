package devotion.security.filters;

import devotion.security.authentications.ApiKeyAuthentication;
import devotion.security.managers.CustomAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private final String key;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestKey = request.getHeader("x-api-key");

        if(requestKey == null || requestKey.equals("null")) {
            doFilter(request, response, filterChain);
        }

        AuthenticationManager customAuthenticationManager = new CustomAuthenticationManager(key);
        ApiKeyAuthentication apiKeyAuthentication = new ApiKeyAuthentication(requestKey, false);
        Authentication result = customAuthenticationManager.authenticate(apiKeyAuthentication);

        if (result.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(result);
            doFilter(request, response, filterChain);
        }
    }
}
