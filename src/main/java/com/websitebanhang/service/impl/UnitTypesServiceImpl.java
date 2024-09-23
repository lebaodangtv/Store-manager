package com.websitebanhang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websitebanhang.entitys.UnitTypes;
import com.websitebanhang.repository.UnitTypeRepo;
import com.websitebanhang.service.UnitTypeService;

@Service
public class UnitTypesServiceImpl implements UnitTypeService {
	
	@Autowired
	UnitTypeRepo repo;

	@Override
	public List<UnitTypes> findAll() {
		return repo.findAll();
	}

	

	
}
