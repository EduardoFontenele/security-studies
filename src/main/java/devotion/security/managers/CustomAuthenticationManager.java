package devotion.security.managers;

import devotion.security.providers.ApiKeyAuthenticationProvider;
import devotion.security.providers.ProvidersContext;
import devotion.security.providers.UsernamePwdAuthenticationProvider;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    @Setter
    private String key;
    private final UsernamePwdAuthenticationProvider usernamePwdAuthenticationProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        ApiKeyAuthenticationProvider apiKeyAuthenticationProvider = new ApiKeyAuthenticationProvider(key);

        if(usernamePwdAuthenticationProvider.supports(authentication.getClass())) {
            return usernamePwdAuthenticationProvider.authenticate(authentication);
        }

        authentication.setAuthenticated(false);
        return authentication;
    }
}
