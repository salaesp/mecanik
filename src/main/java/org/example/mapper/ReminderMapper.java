package org.example.mapper;

import org.example.dto.CarDto;
import org.example.dto.ReminderDto;
import org.example.entity.CarEntity;
import org.example.entity.ReminderEntity;
import org.example.model.Car;
import org.example.model.Reminder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ReminderMapper {

    Reminder toModel(ReminderEntity entity);

    ReminderEntity toEntity(Reminder model);

    ReminderDto toDto(Reminder model);

    Reminder toModel(ReminderDto carDto);
}
