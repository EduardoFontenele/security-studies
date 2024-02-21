package devotion.security.managers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class CustomAuthenticationManager implements AuthenticationManager {
    private final List<AuthenticationProvider> authenticationProviders;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        for (AuthenticationProvider provider : authenticationProviders) {
            if (provider.supports(authentication.getClass())) {
                Authentication auth = provider.authenticate(authentication);
                if (auth != null && auth.isAuthenticated()) {
                    return auth;
                }
            }
        }

        authentication.setAuthenticated(false);
        return authentication;
    }
}