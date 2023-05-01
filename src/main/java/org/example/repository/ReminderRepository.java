package org.example.repository;


import org.example.entity.ReminderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ReminderRepository extends JpaRepository<ReminderEntity, Long> {
    Optional<ReminderEntity> findByIdAndCarIdAndDeleted(Long id, Long carId, boolean deleted);
    List<ReminderEntity> findByCarIdAndDeletedFalse(Long carId);
    List<ReminderEntity> findByCarId(Long carId);
}