package org.example.repository;


import org.example.model.AppUserEntity;
import org.example.model.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface CarRepository extends JpaRepository<CarEntity, Long> {

    Optional<CarEntity> findByUserIdAndId(Long userId, Long id);

    Optional<CarEntity> findByUserIdAndIdAndDeletedFalse(Long userId, Long id);

    List<CarEntity> findByUserIdAndDeletedFalse(Long userId);

    List<CarEntity> findByUserId(Long userId);
}