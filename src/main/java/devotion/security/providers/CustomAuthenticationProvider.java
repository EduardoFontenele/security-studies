package devotion.security.providers;

import devotion.entity.RoleEntity;
import devotion.entity.UserEntity;
import devotion.repository.UserRepository;
import devotion.security.userDetails.RoleSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserEntity foundUser = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new BadCredentialsException(String.format("User with username %s not found", authentication.getName())));

        return new UsernamePasswordAuthenticationToken(foundUser.getUsername(), foundUser.getPassword(), buildAuthorities(foundUser.getRole()));
    }

    private Collection<? extends GrantedAuthority> buildAuthorities(RoleEntity role) {
        return Set.of(new RoleSecurity(role));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
