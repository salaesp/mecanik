package org.example.service;

import org.example.entity.ReminderEntity;
import org.example.entity.RepairmentInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RepairmentService {
    @Autowired
    private CarService carService;

    public void createReminder(Long carId, LocalDateTime reminder) {
        //Aca se rompe todo y es donde tengo que hacer el model
        carService.getCarById(carId);
        RepairmentInfoEntity repairmentInfoEntity = RepairmentInfoEntity.builder()
                .reminder(ReminderEntity.builder()
                        .date(reminder)
                        .build())
                .build();
    }
}
