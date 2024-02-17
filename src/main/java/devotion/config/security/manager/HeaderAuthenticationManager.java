package devotion.config.security.manager;

import devotion.config.security.provider.HeaderAuthenticationProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class HeaderAuthenticationManager implements AuthenticationManager {

    private final HeaderAuthenticationProvider headerAuthenticationProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(headerAuthenticationProvider.supports(authentication.getClass())) {
            return headerAuthenticationProvider.authenticate(authentication);
        }

        throw new ProviderNotFoundException("Provider not found");
    }
}
