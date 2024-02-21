package devotion.security.authentications;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
@RequiredArgsConstructor
public class UsernamePwdAuthentication implements Authentication {
    private boolean authenticated;
    private String username;
    private String password;
    @Getter
    private final String encodedInfo;
    private Set<GrantedAuthority> rolesAndAuthorities;


    @Override
    public String getName() {
        return username;
    }
    @Override
    public Object getCredentials() {
        return password;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.EMPTY_SET;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        authenticated = isAuthenticated;
    }

}
