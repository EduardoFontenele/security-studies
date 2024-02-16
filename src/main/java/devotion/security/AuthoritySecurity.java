package devotion.security;

import devotion.entity.Authority;
import org.springframework.security.core.GrantedAuthority;

public class AuthoritySecurity implements GrantedAuthority {
    private final Authority authority;

    public AuthoritySecurity(Authority authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority.getName();
    }
}
