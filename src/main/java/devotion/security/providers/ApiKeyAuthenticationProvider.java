package devotion.security.providers;

import devotion.security.authentications.ApiKeyAuthentication;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@AllArgsConstructor
public class ApiKeyAuthenticationProvider implements AuthenticationProvider {

    private final String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        ApiKeyAuthentication apiKeyAuthentication = (ApiKeyAuthentication) authentication;

        if(apiKeyAuthentication.getKey().equals(key)) {
            apiKeyAuthentication.setAuthenticated(true);
            return apiKeyAuthentication;
        }

        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ApiKeyAuthentication.class.equals(authentication);
    }
}
