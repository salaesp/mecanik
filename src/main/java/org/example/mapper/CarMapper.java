package org.example.mapper;

import org.example.dto.CarDto;
import org.example.model.CarEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CarMapper {

    @Mappings({@Mapping(source = "creationYear", target = "year")})
    CarDto toDto(CarEntity carEntity);

    @Mappings({@Mapping(source = "year", target = "creationYear")})
    CarEntity toEntity(CarDto carDto);
}
