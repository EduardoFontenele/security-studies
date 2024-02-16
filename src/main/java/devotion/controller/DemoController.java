package devotion.controller;

import devotion.entities.Authority;
import devotion.entities.User;
import devotion.repository.AuthorityRepository;
import devotion.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class DemoController {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    @GetMapping("/public")
    public String hello() {
        return "I should be public";
    }

    @PostMapping("/create_user")
    public void create(@RequestBody User user) {
        Authority read = authorityRepository.findById(1).get();
        user.setAuthorities(Set.of(read));
        userRepository.save(user);
    }

    @GetMapping("/users")
    public List<User> getUser() {
        return userRepository.findAll();
    }
}
