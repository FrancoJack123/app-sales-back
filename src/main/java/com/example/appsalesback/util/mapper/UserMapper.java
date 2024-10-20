package com.example.appsalesback.util.mapper;

import com.example.appsalesback.persistence.entity.User;
import com.example.appsalesback.presentation.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}
