package org.example.mapper;

import org.example.dto.UserDto;
import org.example.entity.AppUserEntity;
import org.example.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(AppUserEntity userEntity);

    User toModel(AppUserEntity userEntity);

    UserDto toDto(User model);

}
