package devotion.config;

import devotion.entity.RoleEntity;
import devotion.entity.UserEntity;
import devotion.repository.RoleRepository;
import devotion.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class BootstrapApplication implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        loadRoles();
        loadUsers();
    }

    private void loadRoles() {
        if (roleRepository.count() == 0) {
            roleRepository.saveAll(List.of(
                    new RoleEntity(null, "ROLE_ADMIN"),
                    new RoleEntity(null, "ROLE_USER"))
            );
        }
    }

    private void loadUsers() {
        if (userRepository.count() == 0) {
            userRepository.save(new UserEntity(
                    null,
                    "admin",
                    "admin",
                    roleRepository.findById(1).orElse(new RoleEntity(10, "ROLE_ADMIN"))
                    ));
        }
    }
}
