package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.controller.request.CreateUserRequest;
import org.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    public final UserService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody @Valid CreateUserRequest req) {
        service.createUser(req.getEmail(), req.getPassword());
    }
}
