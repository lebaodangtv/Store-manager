package com.websitebanhang.service.impl;

import java.util.List;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websitebanhang.dto.CartDetailDto;
import com.websitebanhang.entitys.OrderDetails;

import com.websitebanhang.repository.OrderDetailsRepo;
import com.websitebanhang.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

	@Autowired
	private OrderDetailsRepo repo;

	@Transactional(value = Transactional.TxType.REQUIRED)
	@Override
	public void insert(CartDetailDto cartDetailDto) {
		repo.insert(cartDetailDto);
	}

	@Override
	public List<OrderDetails> findByOrderID(Long id) {
		return repo.findByOrderID(id);
	}

	@Override
	public Double orderDetailsSumPrice(Long orderId) {
		return repo.orderDetailSumPrice(orderId);
	}

	
}
