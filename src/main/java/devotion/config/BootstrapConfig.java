package devotion.config;

import devotion.entity.Authority;
import devotion.entity.User;
import devotion.repository.AuthorityRepository;
import devotion.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class BootstrapConfig implements CommandLineRunner {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    @Override
    public void run(String... args) throws Exception {
        Authority read = new Authority(null, "read");
        authorityRepository.save(read);

        User user = new User(null, "admin", "admin", Set.of(read));
        userRepository.save(user);
    }
}
