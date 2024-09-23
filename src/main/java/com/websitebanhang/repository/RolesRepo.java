package com.websitebanhang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.websitebanhang.entitys.Roles;

@Repository
public interface RolesRepo extends JpaRepository<Roles, String>{
	Roles findByDescription(String description);
}
