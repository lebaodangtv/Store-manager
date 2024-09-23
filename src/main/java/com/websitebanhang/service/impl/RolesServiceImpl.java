package com.websitebanhang.service.impl;

import com.websitebanhang.dto.reponse.PageResponse;
import com.websitebanhang.dto.reponse.RolesRequest;
import com.websitebanhang.entitys.Permission;
import com.websitebanhang.mapper.mapstruct.RolesMapper;
import com.websitebanhang.repository.PermissionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.websitebanhang.entitys.Roles;
import com.websitebanhang.repository.RolesRepo;
import com.websitebanhang.service.RolesService;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RolesServiceImpl implements RolesService {
	
	@Autowired
	RolesRepo rolesRepo;
	@Autowired
	RolesMapper rolesMapper;
	@Autowired
	PermissionRepo permissionRepo;
	
	@Override
	public Roles findByDescription(String description) {
		return rolesRepo.findByDescription(description);
	}

	@Override
	public Object create(RolesRequest rolesRequest) {
		Roles roles = rolesMapper.toEntity(rolesRequest);
		var permission = permissionRepo.findAllByName(rolesRequest.getPermission());
		roles.setPermission(new HashSet<>(permission));
		roles = rolesRepo.save(roles);
		return rolesMapper.toDto(roles);
	}


	@Override
	public Object find(Integer page, Integer limit) {
		Pageable pageable = PageRequest.of(page,limit);
		Page<Roles> pages = rolesRepo.findAll(pageable);
		if(!pages.isEmpty()){
			return PageResponse
					.builder()
					.total(pages.getTotalElements())
					.page(pages.getNumber())
					.limit(pages.getSize())
					.items(Collections.singletonList(pages.getContent().stream()
							.map(rolesMapper::toDto).toList()))
					.build();
		}
		return null;
	}

	@Override
	public Object delete(String name) {
		rolesRepo.deleteById(name);
		return "Xóa thành công";
	}

}
