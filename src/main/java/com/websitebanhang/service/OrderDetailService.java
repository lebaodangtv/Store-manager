package com.websitebanhang.service;

import java.util.List;

import com.websitebanhang.dto.CartDetailDto;
import com.websitebanhang.entitys.OrderDetails;

public interface OrderDetailService {
	void insert(CartDetailDto cartDetailDto);
	List<OrderDetails> findByOrderID(Long id);
}
