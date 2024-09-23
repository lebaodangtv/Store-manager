package com.websitebanhang.service;

import com.websitebanhang.dto.CartDto;
import com.websitebanhang.entitys.Users;

public interface CartService {
	/*
	 * thêm mới, cộng dồn, thay thế, delete truyền cart, thêm: cung cấp id, update
	 * SoLuong, khi nào cộng dồn khi nào thay thế kích hoạt isreplace
	 */
	CartDto updateCart(CartDto cart, Long productId, Integer quantity, boolean isReplace);
	/* update tổng số lượng 
	 *
	 */
	Integer getTotalQuantity(CartDto cart);
	/* 
	 * set lại số lượng
	 */
	Double getTotalPrice(CartDto cart);
	
	void insert(CartDto cartDto, Users user, String address, String phone ) throws Exception;
}
