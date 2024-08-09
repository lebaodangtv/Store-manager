package com.websitebanhang.repository;

import com.websitebanhang.entitys.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PermissionRepo extends JpaRepository<Permission, String> {
}
