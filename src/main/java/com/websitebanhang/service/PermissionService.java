package com.websitebanhang.service;

import com.websitebanhang.dto.reponse.PermissionRequest;

public interface PermissionService {
    Object create(PermissionRequest permissionRequest);

    Object find(Integer page, Integer limit);

    Object delete(Long id);
}
