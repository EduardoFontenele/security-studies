package devotion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsService() {
        UserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();

        UserDetails admin = User.builder()
                .username("admin")
                .password("admin")
                .authorities("write")
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password("user")
                .authorities("read")
                .build();

        inMemoryUserDetailsManager.createUser(admin);
        inMemoryUserDetailsManager.createUser(user);

        return inMemoryUserDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(req -> {
                    req.requestMatchers(HttpMethod.GET, "/demo/1").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/demo/2").hasAuthority("write");
                    req.requestMatchers(HttpMethod.GET, "/demo/3").hasAuthority("read");
                    req.requestMatchers(HttpMethod.GET, "/demo/4").hasAnyAuthority("write", "read");
                    req.anyRequest().denyAll();
                })
                .build();
    }
}
