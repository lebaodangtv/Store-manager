package com.websitebanhang.mapper.mapstruct;

import com.websitebanhang.dto.reponse.PermissionRequest;
import com.websitebanhang.dto.request.PermissionReponse;
import com.websitebanhang.entitys.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toEntity(PermissionRequest permissionRequest);
    PermissionReponse toDto(Permission permission);
}
