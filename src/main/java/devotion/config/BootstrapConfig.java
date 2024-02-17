package devotion.config;

import devotion.entity.UserEntity;
import devotion.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BootstrapConfig implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        UserEntity user = new UserEntity();
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("admin"));

        userRepository.save(user);
    }
}
