package com.websitebanhang.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.websitebanhang.entitys.ProductTypes;

@Repository
public interface ProductTypeRepo extends JpaRepository<ProductTypes, Long> {

	List<ProductTypes> findByIsDeleted(Boolean isDeleted);
	ProductTypes findByName(String name);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE product_types SET description = ?1, slug = ?2 WHERE name = ?3 ", nativeQuery = true)
	void updateProductType(String description, String slug, String name);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE product_types SET isDeleted = 1 WHERE name = ?1 ", nativeQuery = true)
	void deleteProductType(String name);
	
}
