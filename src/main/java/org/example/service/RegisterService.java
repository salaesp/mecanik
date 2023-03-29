package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.exception.UserAlreadyExistsException;
import org.example.model.AppUser;
import org.example.model.AppUserRole;
import org.example.repository.AuthAppUserRepository;
import org.example.utils.EmailUtils;
import org.example.utils.JWTUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegisterService {

    private final AuthAppUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;


    public Map<String, String> createUser(String email, String password) {
        Optional<AppUser> byEmail = repository.findByCleanEmail(EmailUtils.cleanEmail(email));
        if (byEmail.isEmpty()) {
            String encodedPassword = passwordEncoder.encode(password);
            AppUser user = repository.save(buildUserForAuthentication(email, encodedPassword, Set.of(AppUserRole.CAR_OWNER)));
            log.info("Creating user with email {}", email);
            return Collections.singletonMap("jwt-token", jwtUtil.generateToken(user.getEmail()));
        } else {
            log.info("User with email {} already exists", email);
            throw new UserAlreadyExistsException();
        }
    }

    private AppUser buildUserForAuthentication(String email, String password, Set<AppUserRole> roles) {
        return AppUser.builder()
                .cleanEmail(EmailUtils.cleanEmail(email))
                .email(email)
                .password(password)
                .enabled(true)
                .locked(false)
                .appUserRoles(roles)
                .build();
    }

}