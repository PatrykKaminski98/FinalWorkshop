package pl.coderslab.validators;

import pl.coderslab.account.appUser.editPassword.NewPasswordRequest;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EditPasswordMatchesValidator
        implements ConstraintValidator<EditPasswordMatches, Object> {

    @Override
    public void initialize(EditPasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        NewPasswordRequest passwordsRequest = (NewPasswordRequest) obj;
        return passwordsRequest.getPassword().equals(passwordsRequest.getConfirmPassword());
    }
}