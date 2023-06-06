package org.example.mapper;

import org.example.dto.CarDto;
import org.example.dto.CarRelationshipDto;
import org.example.entity.CarEntity;
import org.example.entity.CarRelationshipEntity;
import org.example.model.Car;
import org.example.model.CarRelationship;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CarMapper {

    @Mappings({@Mapping(source = "creationYear", target = "year")})
    Car toModel(CarEntity carEntity);

    @Mappings({@Mapping(source = "year", target = "creationYear")})
    CarEntity toEntity(Car Car);

    CarDto toDto(Car Car);

    Car toModel(CarDto carDto);

    CarRelationship toModel(CarRelationshipEntity entity);

    CarRelationshipEntity toEntity(CarRelationship model);

    CarRelationshipDto toDto(CarRelationship model);
}
