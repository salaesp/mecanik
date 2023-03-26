package org.example.controller.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotEmpty;

@Data
public class CreateUserRequest {
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String password;
}
