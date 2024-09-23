package com.websitebanhang.service.impl;

import java.util.HashMap;


import jakarta.transaction.Transactional;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websitebanhang.dto.CartDetailDto;
import com.websitebanhang.dto.CartDto;
import com.websitebanhang.entitys.Orders;
import com.websitebanhang.entitys.Products;
import com.websitebanhang.entitys.Users;
import com.websitebanhang.service.CartService;
import com.websitebanhang.service.OrderDetailService;
import com.websitebanhang.service.OrdersService;
import com.websitebanhang.service.ProductsService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private ProductsService productsService;

	@Autowired
	private OrdersService ordersService;

	@Autowired
	private OrderDetailService orderDetailService;

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
		cart.setTotalQuantity(getTotalQuantity(cart));
		// update totalPrice
		cart.setTotalPrice(getTotalPrice(cart));
		return null;
	}

	@Override
	public Integer getTotalQuantity(CartDto cart) {
		Integer totalQuantity = 0;
		HashMap<Long, CartDetailDto> details = cart.getDetails();
		for (CartDetailDto cartDetailDto : details.values()) {
			totalQuantity += cartDetailDto.getQuantity();
		}
		return totalQuantity;
	}

	@Override
	public Double getTotalPrice(CartDto cart) {
		Double totalPrice = 0D;
		HashMap<Long, CartDetailDto> details = cart.getDetails();
		for (CartDetailDto cartDetail : details.values()) {
			totalPrice += (cartDetail.getPrice() * cartDetail.getQuantity());
		}
		return totalPrice;
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

	@Transactional(rollbackOn = { Exception.class })
	@Override
	public void insert(CartDto cartDto, Users user, String address, String phone) throws Exception {
		
		if(StringUtils.isAnyBlank(address,phone)) {
			throw new Exception("Address or phone must be not null or empty or whitespace");
		}
		
		// thao tác tới 3 bảng: order, oder_details, product
		
		// 1. insert vào bảng orders trướt
		Orders order = new Orders();
		order.setUser(user);
		order.setAddress(address);
		order.setPhone(phone);

		// 2. orders được trả về sao khi insert
		Orders orderResponse = ordersService.insert(order);

		// 3. nếu orders insert vào hóa đơn bị tạch
		if (ObjectUtils.isEmpty(orderResponse)) {
			throw new Exception("Insert into order table failed");
		}
		
		// 4. insert vào bảng Order_details
		
		for(CartDetailDto cartDetailDto: cartDto.getDetails().values()) {
			cartDetailDto.setOrderId(orderResponse.getId());
			orderDetailService.insert(cartDetailDto);
			
			// update new quantity cho bảng products
			Products product = productsService.findById(cartDetailDto.getProductId());
			Integer newQuantity = product.getQuantity() - cartDetailDto.getQuantity();
			productsService.updateQuantity(newQuantity, product.getId());
		}
	}
}
