package devotion.service;

import devotion.dto.UserInfoDto;
import devotion.dto.UserRegisterDto;
import devotion.entity.RoleEntity;
import devotion.entity.UserEntity;
import devotion.repository.RoleRepository;
import devotion.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    @Override
    @Transactional
    public void createUser(UserRegisterDto userRegisterDto) {
        RoleEntity userRole = roleRepository.findById("ROLE_USER").orElseThrow(() -> new NoSuchElementException("Could not find ROLE_USER role"));

        UserEntity userEntity = new UserEntity(
                userRegisterDto.getUsername(),
                passwordEncoder.encode(userRegisterDto.getPassword()),
                Set.of(userRole)
        );

        userRepository.save(userEntity);
    }

    @Override
    public List<UserInfoDto> listAll() {
        Set<String> roleEntities = new HashSet<>();
        return userRepository.findAll().stream()
                .map(entity ->  {
                    return new UserInfoDto(
                            entity.getUsername(),
                            entity.getRoles().stream().map(RoleEntity::getName).collect(Collectors.toSet()));
                })
                .toList();
    }
}
