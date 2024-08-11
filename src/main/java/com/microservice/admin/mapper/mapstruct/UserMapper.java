package com.microservice.admin.mapper.mapstruct;

import com.microservice.admin.dto.reponse.UserRequest;
import com.microservice.admin.dto.request.UserReponse;
import com.microservice.admin.entitys.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    Users toUser(UserRequest userRequest);
    UserReponse toDto(Users users);
}
