package com.microservice.admin.mapper.mapstruct;

import com.microservice.admin.dto.reponse.PermissionRequest;
import com.microservice.admin.entitys.Permission;
import com.microservice.admin.dto.request.PermissionReponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toEntity(PermissionRequest permissionRequest);
    PermissionReponse toDto(Permission permission);
}
