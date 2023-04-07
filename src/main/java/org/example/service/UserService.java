package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.UserDto;
import org.example.exception.UnexpectedException;
import org.example.mapper.UserMapper;
import org.example.model.AppUserEntity;
import org.example.repository.AuthAppUserRepository;
import org.example.utils.ContextUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final AuthAppUserRepository repository;
    private final UserMapper mapper;

    public UserDto getUser() {
        Optional<AppUserEntity> byId = repository.findById(ContextUtils.getUserId());
        if (byId.isEmpty()) {
            log.error("This should never happen. A logged user tried to get himself from the database but he does not exist");
            throw new UnexpectedException();
        }
        return mapper.toDto(byId.get());
    }
}
