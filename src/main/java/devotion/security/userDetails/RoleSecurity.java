package devotion.security.userDetails;

import devotion.entity.RoleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public class RoleSecurity implements GrantedAuthority {
    private final RoleEntity roleEntity;
    @Override
    public String getAuthority() {
        return roleEntity.getName();
    }
}
