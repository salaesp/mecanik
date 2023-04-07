package org.example.validators;

import org.example.conditions.HasPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordValidation, HasPassword> {

    public boolean isValid(HasPassword hasPassword, ConstraintValidatorContext cxt) {
        return hasPassword.getPassword().equals(hasPassword.getRepeatPassword());
    }
}
