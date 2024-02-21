package devotion.security.config;

import devotion.security.filters.ApiKeyAuthenticationFilter;
import devotion.security.filters.UsernamePwdAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UsernamePwdAuthenticationFilter usernamePwdAuthenticationFilter;
    private final ApiKeyAuthenticationFilter apiKeyAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(req -> {
                    req.anyRequest().authenticated();
                })
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(usernamePwdAuthenticationFilter, BasicAuthenticationFilter.class)
                .addFilterBefore(apiKeyAuthenticationFilter, BasicAuthenticationFilter.class)
                .build();
    }

}
