package org.example.mapper;

import org.example.dto.UserDto;
import org.example.entity.AppUserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(AppUserEntity userEntity);

}
