package com.microservice.admin.service;

import com.microservice.admin.dto.reponse.PermissionRequest;

public interface PermissionService {
    Object create(PermissionRequest permissionRequest);

    Object find(Integer page, Integer limit);

    Object delete(String name);
}
