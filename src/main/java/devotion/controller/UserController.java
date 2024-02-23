package devotion.controller;

import devotion.dto.UserInfoDto;
import devotion.dto.UserRegisterDto;
import devotion.service.UsersServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UsersServiceImpl usersService;

    @PostMapping("/register")
    @PreAuthorize("permitAll")
    public ResponseEntity<Void> register(@RequestBody @Validated UserRegisterDto dto) {
        usersService.createUser(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<UserInfoDto>> listAll() {
        return ResponseEntity.ok(usersService.listAll());
    }
}
