package org.example.controller.request;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String email;
    private String password;
}
