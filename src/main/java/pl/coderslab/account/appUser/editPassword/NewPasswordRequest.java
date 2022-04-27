package pl.coderslab.account.appUser.editPassword;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.validators.EditPasswordMatches;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@EditPasswordMatches
public class NewPasswordRequest {
    @NotNull
    @NotEmpty
    private String oldPassword;
    @NotNull
    @NotEmpty
    private String password;
    private String confirmPassword;
}
