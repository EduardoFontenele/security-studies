package devotion.config.security.managers;

import devotion.config.security.providers.CustomerAuthenticationProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private final CustomerAuthenticationProvider customerAuthenticationProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(customerAuthenticationProvider.supports(authentication.getClass())) {
            return customerAuthenticationProvider.authenticate(authentication);
        }

        throw new BadCredentialsException("No, not again");
    }
}
