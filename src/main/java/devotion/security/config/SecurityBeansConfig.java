package devotion.security.config;

import devotion.repository.UserRepository;
import devotion.security.managers.CustomAuthenticationManager;
import devotion.security.providers.ApiKeyAuthenticationProvider;
import devotion.security.providers.UsernamePwdAuthenticationProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@Configuration
public class SecurityBeansConfig {
    @Value("${key.secret}")
    private String xApiSecretKey;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UsernamePwdAuthenticationProvider usernamePwdAuthenticationProvider(UserRepository userRepository) {
        return new UsernamePwdAuthenticationProvider(userRepository);
    }

    @Bean
    public ApiKeyAuthenticationProvider apiKeyAuthenticationProvider() {
        return new ApiKeyAuthenticationProvider(xApiSecretKey);
    }

    @Bean
    public CustomAuthenticationManager customAuthenticationManager(UsernamePwdAuthenticationProvider usernamePwdAuthenticationProvider, ApiKeyAuthenticationProvider apiKeyAuthenticationProvider) {
        return new CustomAuthenticationManager(Arrays.asList(usernamePwdAuthenticationProvider, apiKeyAuthenticationProvider));
    }
}
