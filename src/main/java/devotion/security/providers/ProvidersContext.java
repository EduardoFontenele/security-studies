package devotion.security.providers;

import lombok.Getter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProvidersContext {

    private final UsernamePwdAuthenticationProvider usernamePwdAuthenticationProvider;

    @Getter
    private final List<AuthenticationProvider> providers = new ArrayList<>();

    public ProvidersContext(UsernamePwdAuthenticationProvider usernamePwdAuthenticationProvider) {
        this.usernamePwdAuthenticationProvider = usernamePwdAuthenticationProvider;

        providers.add(usernamePwdAuthenticationProvider);
    }


    public void addProvider(AuthenticationProvider provider) {
        providers.add(provider);
    }

    public void resetProvidersToDefault() {
        providers.clear();
    }
}
