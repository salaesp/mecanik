package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.exception.UserAlreadyExistsException;
import org.example.model.AppUser;
import org.example.model.AppUserRole;
import org.example.repository.AuthAppUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
    private final AuthAppUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> byEmail = repository
                .findByEmail(username);
        return byEmail
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username)));
    }

    public void createUser(String email, String password) {
        Optional<AppUser> byEmail = repository.findByEmail(email);
        if (byEmail.isEmpty()) {
            repository.save(buildUserForAuthentication(email, passwordEncoder.encode(password), Set.of(AppUserRole.CAR_OWNER)));
            log.info("Creating user with email {}", email);
        } else {
            log.info("User with email {} already exists", email);
            throw new UserAlreadyExistsException("Cannot use username. Please choose another");
        }
    }

    private AppUser buildUserForAuthentication(String username, String password, Set<AppUserRole> roles) {
        return AppUser.builder()
                .email(username)
                .password(password)
                .enabled(true)
                .locked(false)
                .appUserRoles(roles)
                .build();
    }
}