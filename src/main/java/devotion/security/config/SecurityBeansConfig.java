package devotion.security.config;

import devotion.repository.UserRepository;
import devotion.security.providers.UsernamePwdAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityBeansConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UsernamePwdAuthenticationProvider usernamePwdAuthenticationProvider(UserRepository userRepository) {
        return new UsernamePwdAuthenticationProvider(userRepository);
    }
}
