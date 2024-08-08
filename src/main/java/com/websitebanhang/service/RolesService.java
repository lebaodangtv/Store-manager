package com.websitebanhang.service;

import com.websitebanhang.dto.reponse.RolesRequest;
import com.websitebanhang.entitys.Roles;

public interface RolesService {
	Roles findByDescription(String description);

	Object create(RolesRequest rolesRequest);

	Object find(Integer page, Integer limit);

	Object delete(String name);
}
