package devotion.config;

import devotion.entity.RoleEntity;
import devotion.entity.UserEntity;
import devotion.repository.RoleRepository;
import devotion.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class BootstrapApplication implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        loadRoles();
        loadUsers();
    }

    private void loadRoles() {
        if (roleRepository.count() == 0) {
            roleRepository.saveAll(List.of(
                    new RoleEntity( "ROLE_ADMIN"),
                    new RoleEntity("ROLE_USER"))
            );
        }
    }

    private void loadUsers() {
        if (userRepository.count() == 0) {
            userRepository.save(new UserEntity(
                    null,
                    "admin",
                    passwordEncoder.encode("admin"),
                            Set.of(roleRepository.findAll().get(0))
                    ));
        }
    }
}
