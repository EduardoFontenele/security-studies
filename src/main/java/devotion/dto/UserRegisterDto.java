package devotion.dto;


import devotion.utils.DefaultMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto  {
    @NotBlank(message = DefaultMessages.NOT_BLANK_MESSAGE)
    @Size(min = 5, max = 255, message = DefaultMessages.SIZE_BETWEEN_5_255)
    private String username;

    @NotBlank(message = DefaultMessages.NOT_BLANK_MESSAGE)
    @Size(min = 5, max = 255, message = DefaultMessages.SIZE_BETWEEN_5_255)
    private String password;
}
