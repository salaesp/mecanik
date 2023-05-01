package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.ReminderEntity;
import org.example.exception.ResourceNotFoundException;
import org.example.mapper.ReminderMapper;
import org.example.model.Reminder;
import org.example.repository.ReminderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReminderService {

    private final CarService carService;
    private final ReminderRepository repository;
    private final ReminderMapper mapper;

    public Reminder createReminderForCar(Reminder reminder, Long carId) {
        reminder.setCarId(carId);
        ReminderEntity entity = mapper.toEntity(reminder);
        ReminderEntity save = internalSave(entity);
        return mapper.toModel(save);
    }

    public List<Reminder> listRemindersForCar(Long carId, boolean includeDeleted) {
        List<ReminderEntity> byUserId = includeDeleted ?
                repository.findByCarId(carId) :
                repository.findByCarIdAndDeletedFalse(carId);
        return byUserId
                .stream().map(mapper::toModel)
                .collect(Collectors.toList());
    }

    public Reminder deleteReminder(Long carId, Long id) {
        ReminderEntity entity = this.internalGet(id, carId, false);
        entity.setDeleted(true);
        return mapper.toModel(internalSave(entity));
    }

    private ReminderEntity internalSave(ReminderEntity entity) {
        return repository.save(entity);
    }

    private ReminderEntity internalGet(Long id, Long carId, boolean includeDeleted) {
        return repository.findByIdAndCarIdAndDeleted(id, carId, includeDeleted)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
