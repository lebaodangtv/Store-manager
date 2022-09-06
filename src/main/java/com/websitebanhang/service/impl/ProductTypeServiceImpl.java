package com.websitebanhang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websitebanhang.entitys.ProductTypes;
import com.websitebanhang.repository.ProductTypeRepo;
import com.websitebanhang.service.ProductTypeService;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {
	
	@Autowired
	ProductTypeRepo repo;
	
	@Override
	public List<ProductTypes> fillAll() {
		return repo.findAll();
	}

}
