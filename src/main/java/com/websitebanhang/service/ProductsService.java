package com.websitebanhang.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.websitebanhang.entitys.Products;

public interface ProductsService {
	List<Products> findAll();
	List<Products> productsTypeID(Long typeId);	
	Page<Products> findAll(int pageSize, int pageNumber) throws Exception;
	Products save(Products product);
	Products findByName(String name);
	Products findById(Long id);
	Products findBySlug(String slug);
	void updateProduct(Products product);
	void updateQuantity(Integer newQuatity, Long id);
	void deleteProduct(String name);
}
