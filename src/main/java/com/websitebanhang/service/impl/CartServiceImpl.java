package com.websitebanhang.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websitebanhang.dto.CartDetailDto;
import com.websitebanhang.dto.CartDto;
import com.websitebanhang.entitys.Products;
import com.websitebanhang.service.CartService;
import com.websitebanhang.service.ProductsService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	ProductsService productsService;

	@Override
	public CartDto updateCart(CartDto cart, Long productId, Integer quantity, boolean isReplace) {
		Products product = productsService.findById(productId);
		HashMap<Long, CartDetailDto> details = cart.getDetails();
		/*
		 * 1- thêm mới 2- update + 2.1 cộng dồn + 2.2 thay thế hoàn toàn (isReplace =
		 * true) 3- delte (update vs quantity = 0)
		 */

		// mong muốn key (id sp) ko có nên trả về false nên thêm phủ định
		if (!details.containsKey(productId)) {
			CartDetailDto cartDetailDto = createNewCartDetail(product, quantity);
			details.put(productId, cartDetailDto);
		} else if (quantity > 0) {
			if (isReplace) {
				details.get(productId).setQuantity(quantity);
			} else {
				Integer currentQuantity = details.get(productId).getQuantity();
				Integer newQuantity = currentQuantity + quantity;
				details.get(productId).setQuantity(newQuantity);
			}
		} else {
			details.remove(productId);
		}
		// update totalQuantity
		// update totalPrice
		return null;
	}

	private CartDetailDto createNewCartDetail(Products product, Integer quantity) {
		CartDetailDto cartDetailDto = new CartDetailDto();
		cartDetailDto.setProductId(product.getId());
		cartDetailDto.setQuantity(quantity);
		cartDetailDto.setPrice(product.getPrice());
		cartDetailDto.setImgUrl(product.getImgUrl());
		cartDetailDto.setSlug(product.getSlug());
		cartDetailDto.setName(product.getName());
		return cartDetailDto;
	}

}
