package devotion.security;

import devotion.entity.RoleEntity;
import devotion.entity.UserEntity;
import devotion.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomProvider implements AuthenticationProvider {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserEntity user = userRepository.findByUsername(username).orElse(new UserEntity(null, null, null, null));

        if (user.getPassword() == null) {
            return new UsernamePasswordAuthenticationToken(null, null);
        } else {
            if(user.getPassword().matches(password)) {
                return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), buildAuthorities(user.getRole()));
            }
        }
        return null;
    }

    private Collection<? extends GrantedAuthority> buildAuthorities(RoleEntity role) {
        return List.of(new SimpleGrantedAuthority(role.getName()));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
