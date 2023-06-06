package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.exception.UnexpectedException;
import org.example.mapper.UserMapper;
import org.example.model.User;
import org.example.repository.AuthAppUserRepository;
import org.example.utils.ContextUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final AuthAppUserRepository repository;
    private final UserMapper mapper;

    public User getUser() {
        return mapper.toModel(repository
                .findById(ContextUtils.getUserId())
                .orElseThrow(() -> {
                    log.error("This should never happen. A logged user tried to get himself from the database but he does not exist");
                    throw new UnexpectedException();
                }));
    }
}
