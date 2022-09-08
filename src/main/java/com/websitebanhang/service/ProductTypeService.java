package com.websitebanhang.service;

import java.util.List;

import com.websitebanhang.entitys.ProductTypes;

public interface ProductTypeService {
	List<ProductTypes> findByIsdeleted(); 
	ProductTypes findByName(String name);
	ProductTypes createProductType(ProductTypes productType);
	void updateProductType(ProductTypes productType);
	void deleteProductType(String name);
}
