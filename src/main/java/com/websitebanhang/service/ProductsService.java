package com.websitebanhang.service;

import java.util.List;

import com.websitebanhang.entitys.Products;

public interface ProductsService {
	List<Products> findAll();
	Products findById(Long id);
}
