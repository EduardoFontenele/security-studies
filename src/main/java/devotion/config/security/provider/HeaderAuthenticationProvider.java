package devotion.config.security.provider;

import devotion.config.security.authentication.HeaderAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class HeaderAuthenticationProvider implements AuthenticationProvider {

    @Value("${application.secret}")
    private String secret;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        HeaderAuthentication headerAuthentication = ((HeaderAuthentication) authentication);
        String key = headerAuthentication.getKey();

        if (key.equals(secret)) {
            return new HeaderAuthentication(true, null);
        }

        throw new BadCredentialsException("Bad credentials!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return HeaderAuthentication.class.equals(authentication);
    }
}
