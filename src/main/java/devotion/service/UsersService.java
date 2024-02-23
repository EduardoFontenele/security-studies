package devotion.service;

import devotion.dto.UserInfoDto;
import devotion.dto.UserRegisterDto;

import java.util.List;

public interface UsersService {

    void createUser(UserRegisterDto userRegisterDto);
    List<UserInfoDto> listAll();
}
