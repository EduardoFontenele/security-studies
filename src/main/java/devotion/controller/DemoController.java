package devotion.controller;

import devotion.entity.UserEntity;
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
    public String demo() {
        return "hello, brother";
    }

    @GetMapping("/users")
    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }
}
