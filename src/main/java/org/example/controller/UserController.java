package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserDto;
import org.example.mapper.UserMapper;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService service;
    private final UserMapper mapper;

    @GetMapping("/info")
    public UserDto getUserDetails() {
        return mapper.toDto(service.getUser());
    }
}