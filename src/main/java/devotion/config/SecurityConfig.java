package devotion.config;

import devotion.config.security.filter.HeaderAuthenticationFilter;
import devotion.config.security.filter.LoginAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final HeaderAuthenticationFilter headerAuthenticationFilter;
    private final LoginAuthenticationFilter loginAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .addFilterAt(loginAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(req -> {
                    req.anyRequest().authenticated();
                })
                .build();
    }

}
