package com.websitebanhang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websitebanhang.entitys.Roles;
import com.websitebanhang.repository.RolesRepo;
import com.websitebanhang.service.RolesService;

@Service
public class RolesServiceImpl implements RolesService {
	
	@Autowired
	RolesRepo rolesRepo; 
	
	@Override
	public Roles findByDescription(String description) {
		return rolesRepo.findByDescription(description);
	}
	
}
