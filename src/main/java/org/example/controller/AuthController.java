package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.controller.request.CreateUserRequest;
import org.example.controller.request.LoginCredentials;
import org.example.security.service.LoginService;
import org.example.service.RegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    public final LoginService loginService;
    public final RegisterService registerService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> createUser(@RequestBody @Validated CreateUserRequest req) {
        return registerService.createUser(req.getEmail(), req.getPassword());
    }


    @PostMapping("/login")
    public Map<String, String> loginHandler(@RequestBody LoginCredentials body) {
        return loginService.loginUser(body.getEmail(), body.getPassword());
    }

}
