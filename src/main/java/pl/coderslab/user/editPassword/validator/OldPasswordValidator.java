/*package pl.coderslab.login.validator;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.coderslab.user.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OldPasswordValidator implements ConstraintValidator<oldPassword, String> {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserService userService;

    public OldPasswordValidator(BCryptPasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    public void initialize(oldPassword constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if ( s == null ) {
            return true;
        }
        return passwordEncoder.matches(s, userService.)
    }
}
*/