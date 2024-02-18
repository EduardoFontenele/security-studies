package devotion.config;

import devotion.entity.RoleEntity;
import devotion.entity.UserEntity;
import devotion.repository.RoleRepository;
import devotion.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@AllArgsConstructor
public class BootstrapConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        loadRoles();
        loadUsers();
    }
    private void loadRoles() {
        roleRepository.save(new RoleEntity(null, "ROLE_ADMIN"));
        roleRepository.save(new RoleEntity(null, "ROLE_USER"));
    }


    private void loadUsers() {
        UserEntity admin = new UserEntity();
        RoleEntity adminRole = roleRepository.findById(1).orElseThrow(() -> new NoSuchElementException("No such role present"));

        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setRole(adminRole);

        userRepository.save(admin);

        UserEntity user = new UserEntity();
        RoleEntity userRole = roleRepository.findById(2).orElseThrow(() -> new NoSuchElementException("No such role present"));

        user.setUsername("user");
        user.setPassword("user");
        user.setRole(userRole);

        userRepository.save(user);
    }
}
