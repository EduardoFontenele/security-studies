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
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
public class ApiKeyFilter extends OncePerRequestFilter {

    private final String key;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String requestKey = request.getHeader("x-api-key");

        // this part here is very, VERY important
        // always remember to proceed to the next filter if your filter lack what it needs to authenticate
        if(requestKey == null || requestKey.equals("null")) doFilter(request, response, filterChain);

        ApiKeyAuthentication apiKeyAuthentication = new ApiKeyAuthentication(requestKey);
        AuthenticationManager authenticationManager = new CustomAuthenticationManager(key);

        try {
            Authentication result = authenticationManager.authenticate(apiKeyAuthentication);
            if (result.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(result);
                doFilter(request, response, filterChain);
            }

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        } catch (AuthenticationException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }
}
