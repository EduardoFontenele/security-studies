package devotion.controller;

import devotion.security.CustomProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final CustomProvider customProvider;

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public void login() {}
}
