package com.websitebanhang.service.impl;

import com.websitebanhang.dto.reponse.PageResponse;
import com.websitebanhang.dto.reponse.PermissionRequest;
import com.websitebanhang.entitys.Permission;
import com.websitebanhang.mapper.mapstruct.PermissionMapper;
import com.websitebanhang.repository.PermissionRepo;
import com.websitebanhang.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionRepo permissionRepo;
    @Autowired
    PermissionMapper permissionMapper;
    @Override
    public Object create(PermissionRequest permissionRequest) {
        Permission permission = permissionMapper.toEntity(permissionRequest);
        return permissionMapper.toDto(permissionRepo.saveAndFlush(permission));
    }

    @Override
    public Object find(Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page,limit);
        Page<Permission>  pages = permissionRepo.findAll(pageable);
        if(!pages.isEmpty()){
            return PageResponse
                    .builder()
                    .total(pages.getTotalElements())
                    .page(pages.getNumber())
                    .limit(pages.getSize())
                    .items(Collections.singletonList(pages.getContent()))
                    .build();
        }
        return null;
    }

    @Override
    public Object delete(Long id) {
        permissionRepo.deleteById(id);
        return "Xóa thành công";
    }
}
