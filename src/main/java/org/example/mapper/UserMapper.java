package org.example.mapper;

import org.example.dto.CarDto;
import org.example.dto.UserDto;
import org.example.model.AppUserEntity;
import org.example.model.CarEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(AppUserEntity userEntity);

}
