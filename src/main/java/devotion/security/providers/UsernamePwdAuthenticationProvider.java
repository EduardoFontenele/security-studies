package devotion.security.providers;

import devotion.entity.UserEntity;
import devotion.repository.UserRepository;
import devotion.security.authentications.UsernamePwdAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UsernamePwdAuthenticationProvider implements AuthenticationProvider {
    private final UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePwdAuthentication usernamePwdAuthentication = (UsernamePwdAuthentication) authentication;
        String encodedInfo = usernamePwdAuthentication.getEncodedInfo();
        byte[] decodedBytes = Base64.getDecoder().decode(encodedInfo);
        String decodedInfo = new String(decodedBytes, StandardCharsets.UTF_8);
        String[] userSplitInformation = decodedInfo.split(":");
        String username = userSplitInformation[0];

        Optional<UserEntity> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            String password = userSplitInformation[1];

            if (user.get().getPassword().equals(password)) {
                return new UsernamePwdAuthentication(
                        true,
                        username,
                        null,
                        null,
                        null);
            } else {
                return new UsernamePwdAuthentication(
                        false,
                        username,
                        null,
                        null,
                        null);
            }
        } else {
            throw new BadCredentialsException("Something went wrong while trying to authenticate using username and password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePwdAuthentication.class.equals(authentication);
    }
}
