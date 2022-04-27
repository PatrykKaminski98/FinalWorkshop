package pl.coderslab.account.registration;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.validators.PasswordMatches;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@PasswordMatches
public class UserDto {
    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String password;
    private String confirmPassword;

    @NotNull
    @NotEmpty
    @Email(message = "Niepoprawny adres email")
    private String email;
}
