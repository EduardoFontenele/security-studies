package devotion.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UserLoginDto(
        @NotNull @Min(6) String username,
        @NotNull @Min(6) String password,
        @NotNull List<RoleDto> roles
) {
}
