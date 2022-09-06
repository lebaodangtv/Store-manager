package com.websitebanhang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.websitebanhang.dto.CartDetailDto;
import com.websitebanhang.entitys.OrderDetails;

@Repository
public interface OrderDetailsRepo extends JpaRepository<OrderDetails, Long> {

	// những câu query insert, update, delete thêm @modifying
	@Modifying(clearAutomatically = true)
	@Query(value = "INSERT INTO order_details(orderId, productId,price,quantity)"
			+ " VALUES (:#{#dto.orderId}, :#{#dto.productId}, :#{#dto.price}, :#{#dto.quantity})", nativeQuery = true)
	void insert(@Param("dto") CartDetailDto cartDetailDto);
	
}
