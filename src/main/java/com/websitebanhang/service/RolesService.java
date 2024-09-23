package com.websitebanhang.service;

import com.websitebanhang.entitys.Roles;

public interface RolesService {
	Roles findByDescription(String description);
}
