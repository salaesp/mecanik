package org.example.controller.request;

import lombok.Data;
import org.example.conditions.HasPassword;
import org.example.validators.PasswordValidation;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@PasswordValidation
public class CreateUserRequest implements HasPassword {
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String repeatPassword;
    private String name;
}
