package org.example.controller.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class CreateUserRequest {
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
