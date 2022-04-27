package pl.coderslab.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE,ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = EditPasswordMatchesValidator.class)
@Documented
public @interface EditPasswordMatches {
    String message() default "Hasła różnią się od siebie";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}