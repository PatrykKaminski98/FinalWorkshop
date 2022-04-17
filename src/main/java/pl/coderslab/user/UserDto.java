package pl.coderslab.user;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.user.register.PasswordMatches;
import pl.coderslab.user.register.ValidEmail;

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
    @ValidEmail
    private String email;
}
