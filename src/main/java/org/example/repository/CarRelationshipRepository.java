package org.example.repository;


import org.example.entity.CarRelationshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface CarRelationshipRepository extends JpaRepository<CarRelationshipEntity, Long> {

    Optional<CarRelationshipEntity> findByUserIdAndCarId(Long userId, Long id);

    Optional<CarRelationshipEntity> findByUserIdAndCarIdAndDeletedFalse(Long userId, Long id);

    List<CarRelationshipEntity> findByUserIdAndDeletedFalse(Long userId);

    List<CarRelationshipEntity> findByUserId(Long userId);
}