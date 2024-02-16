package devotion.controller;

import devotion.entity.User;
import devotion.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DemoController {

    private final UserRepository userRepository;

    @GetMapping("/demo")
    public String hello() {
        return "Hello, I'm a demo!";
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
