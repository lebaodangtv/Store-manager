package com.websitebanhang.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.websitebanhang.entitys.Products;

@Repository
public interface ProductsRepo extends JpaRepository<Products, Long> {
	List<Products> findByProductTypes_Id(Long typeId);
	Products findBySlug(String slug);
	Products findByName(String name);
	
	// viết theo truy vấn sql 
	@Query(value = "select * from products where isDeleted = 0 and quantity > 0",
			nativeQuery = true)
	List<Products> findAllAvailable();
	
	// viết truy vấn theo String_data_jpa phiên dịch câu sql
	List<Products> findByIsDeletedAndQuantityGreaterThan(Boolean isDeketed,Integer quantity);
	// phan trang
	Page<Products> findByIsDeletedAndQuantityGreaterThan(Boolean isDeketed,Integer quantity, Pageable pageable);

	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE products SET quantity = ?1 WHERE id= ?2",
			nativeQuery = true)
	void updateQuantity(Integer newQuantity, Long ProductId);
	
	@Query(value = "SELECT * FROM products WHERE typeId = ?1", nativeQuery = true )
	List<Products> getAllType(Long typeID);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE products SET typeID = ?1, quantity = ?2, price = ?3, unitId = ?4, imgUrl = ?5, description = ?6, slug=?7 WHERE name = ?8 ", nativeQuery = true)
	void updateProduct(Long typeID, Integer quantity, Double price,Long unitId, String imgUrl,String description,String slug, String name);
}
