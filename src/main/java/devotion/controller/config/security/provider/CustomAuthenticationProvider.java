package devotion.controller.config.security.provider;

import devotion.controller.config.security.authenticati0n.CustomAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Value("${application.secret}")
    private String secret;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomAuthentication customAuthentication = (CustomAuthentication) authentication;
        String key = customAuthentication.getKey();

        if(key.equals(secret)) {
            return new CustomAuthentication(true, null);
        }

        throw new BadCredentialsException("Secret is invalid");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthentication.class.isAssignableFrom(authentication);
    }
}
