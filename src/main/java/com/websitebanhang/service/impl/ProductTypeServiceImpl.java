package com.websitebanhang.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.ObjectUtils;
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
	public List<ProductTypes> findAll() {
		return repo.findByIsDeleted(Boolean.FALSE);
	}

	@Override
	public ProductTypes findByName(String name) {
		return repo.findByName(name);
	}

	@Override
	@Transactional(rollbackOn = { Exception.class, Throwable.class })
	public ProductTypes createProductType(ProductTypes productType) {
		productType.setIsDeleted(Boolean.FALSE);
		return repo.saveAndFlush(productType);
	}

	@Override
	@Transactional(rollbackOn = { Exception.class, Throwable.class })
	public void updateProductType(ProductTypes productType) {
		if(ObjectUtils.isNotEmpty(productType)) {
			repo.updateProductType(productType.getDescription(), productType.getSlug(), productType.getName());
		}
	}

	@Override
	@Transactional(rollbackOn = { Exception.class, Throwable.class })
	public void deleteProductType(String name) {
		repo.deleteProductType(name);
	}


	

}
