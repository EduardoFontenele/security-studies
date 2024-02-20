package devotion.security.managers;

import devotion.security.providers.ApiKeyAuthenticationProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private final String key;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        ApiKeyAuthenticationProvider apiKeyAuthenticationProvider = new ApiKeyAuthenticationProvider(key);

        if(apiKeyAuthenticationProvider.supports(authentication.getClass())) {
            return apiKeyAuthenticationProvider.authenticate(authentication);
        }

        return null;
    }
}
