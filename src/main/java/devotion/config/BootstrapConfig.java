package devotion.config;

import devotion.entities.Authority;
import devotion.entities.User;
import devotion.repository.AuthorityRepository;
import devotion.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class BootstrapConfig implements CommandLineRunner {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    @Override
    public void run(String... args) throws Exception {
        Authority read = new Authority(1, "read");
        Authority write = new Authority(2, "write");
        authorityRepository.saveAll(Arrays.asList(read, write));

        userRepository.save(new User(null, "admin", "admin",
                Set.of(authorityRepository.findById(1).get())));
    }
}
