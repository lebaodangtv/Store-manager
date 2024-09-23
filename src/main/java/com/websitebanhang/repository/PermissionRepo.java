package com.websitebanhang.repository;

import com.websitebanhang.entitys.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PermissionRepo extends JpaRepository<Permission, String> {
    @Query("""
    select f from Permission f where f.name in :name
    """)
    Set<Permission> findAllByName(@Param("name") Set<String> name);
}
