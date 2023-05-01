package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.dto.ReminderDto;
import org.example.mapper.ReminderMapper;
import org.example.service.ReminderService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cars/{carId}/reminders")
public class ReminderController {

    private final ReminderService service;
    private final ReminderMapper mapper;

    @PostMapping
    @Operation(summary = "Creates a new reminder for car")
    public ReminderDto addReminder(@PathVariable Long carId, @RequestBody @Validated ReminderDto reminder) {
        return mapper.toDto(service.createReminderForCar(mapper.toModel(reminder), carId));
    }

    @GetMapping
    @Operation(summary = "Lists all reminders for car")
    public List<ReminderDto> getCarById(@PathVariable long carId, @RequestParam(value = "include_deleted",
            required = false, defaultValue = "false") boolean includeDeleted) {
        return service.listRemindersForCar(carId, includeDeleted)
                .stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete reminder by id")
    public ReminderDto deleteCar(@PathVariable Long carId, @PathVariable Long id) {
        return mapper.toDto(service.deleteReminder(carId, id));
    }
}
