package devotion.security.providers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProvidersContext {

    private final UsernamePwdAuthenticationProvider usernamePwdAuthenticationProvider;


}
