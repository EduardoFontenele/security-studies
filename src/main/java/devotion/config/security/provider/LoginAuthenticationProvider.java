package devotion.config.security.provider;

import devotion.config.security.authentication.LoginAuthentication;
import devotion.entity.UserEntity;
import devotion.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class LoginAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        LoginAuthentication loginAuthentication = (LoginAuthentication) authentication;

        String authorizationHeader = loginAuthentication.getAuthorization();
        String userToken = authorizationHeader.substring(authorizationHeader.lastIndexOf(" ") + 1);

        String decodedUserTokenString = new String(Base64.getDecoder().decode(userToken));

        String[] splitToken = decodedUserTokenString.split(":");
        String username = splitToken[0];
        String password = splitToken[1];

        UserEntity foundUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("No user with '" + username + "' username present"));

        if(passwordEncoder.matches(password, foundUser.getPassword())) {
            return new LoginAuthentication(true, null, username);
        }

        throw new BadCredentialsException("Something went wrong");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return LoginAuthentication.class.equals(authentication);
    }
}
