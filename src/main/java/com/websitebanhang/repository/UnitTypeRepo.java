package com.websitebanhang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.websitebanhang.entitys.UnitTypes;

@Repository
public interface UnitTypeRepo extends JpaRepository<UnitTypes, Long> {

}
