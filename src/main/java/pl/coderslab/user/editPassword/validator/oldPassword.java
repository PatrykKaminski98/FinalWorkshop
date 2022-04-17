package pl.coderslab.user.editPassword.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
//@Constraint(validatedBy = OldPasswordValidator.class)
public @interface oldPassword {
    String message() default "{Niepoprawne hasło}";
    Class<? extends Payload>[] payload() default {};
}
