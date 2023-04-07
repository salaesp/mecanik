package org.example.repository;


import org.example.model.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AuthAppUserRepository extends JpaRepository<AppUserEntity, Long> {
    Optional<AppUserEntity> findByCleanEmail(String cleanEmail);
}