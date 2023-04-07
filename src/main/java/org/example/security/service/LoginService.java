package org.example.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.repository.AuthAppUserRepository;
import org.example.utils.JWTUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {

    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
    private final AuthAppUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authManager;


    public Map<String, String> loginUser(String email, String password) {
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(email, password);
        authManager.authenticate(authInputToken);
        String token = jwtUtil.generateToken(email);
        return Collections.singletonMap("jwt-token", token);
    }
}