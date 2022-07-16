package com.websitebanhang.service;

import com.websitebanhang.dto.CartDto;

public interface CartService {
	/*
	 * thêm mới, cộng dồn, thay thế, delete truyền cart, thêm: cung cấp id, update
	 * SoLuong, khi nào cộng dồn khi nào thay thế kích hoạt isreplace
	 */
	CartDto updateCart(CartDto cart, Long productId, Integer quantity, boolean isReplace);
}
