package com.websitebanhang.mapper.mapstruct;

import com.websitebanhang.dto.reponse.RolesRequest;
import com.websitebanhang.dto.request.RolesReponse;
import com.websitebanhang.entitys.Roles;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RolesMapper {

    /**
     * @Mapping(target = "id", ignore = true)
     * bỏ qua trường này trong enity không map (tự định nghĩa)
     * @param rolesRequest
     * @return
     */
    @Mapping(target = "name", ignore = true)
    Roles toEntity(RolesRequest rolesRequest);
    @Mapping(target = "name", ignore = true)
    RolesReponse toDto(Roles roles);
}
