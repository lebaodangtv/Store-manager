package com.websitebanhang.mapper;

import com.websitebanhang.dto.UserDto;
import com.websitebanhang.entitys.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    Users toUser(UserDto userDto);
}
