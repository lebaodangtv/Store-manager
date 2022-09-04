package com.websitebanhang.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.websitebanhang.entitys.Products;

public interface ProductsService {
	List<Products> findAll();
	Page<Products> findAll(int pageSize, int pageNumber) throws Exception;
	Products findById(Long id);
	Products findBySlug(String slug);
	void updateQuantity(Integer newQuatity, Long id);
	List<Products> productsTypeID(Long typeId);	
	Products save(Products product);
	
}
