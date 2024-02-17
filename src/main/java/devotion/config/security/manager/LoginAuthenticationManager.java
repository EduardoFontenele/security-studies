package devotion.config.security.manager;

import devotion.config.security.provider.LoginAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Primary
public class LoginAuthenticationManager implements AuthenticationManager {
    private final LoginAuthenticationProvider loginAuthenticationProvider;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (loginAuthenticationProvider.supports(authentication.getClass())) {
            return loginAuthenticationProvider.authenticate(authentication);
        }
        return null;
    }
}
