package com.websitebanhang.mapper.mapstruct;

import com.websitebanhang.dto.reponse.UserRequest;
import com.websitebanhang.dto.request.UserReponse;
import com.websitebanhang.entitys.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    Users toUser(UserRequest userRequest);
    UserReponse toDto(Users users);
}
