package com.websitebanhang.service.impl;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websitebanhang.dto.CartDetailDto;
import com.websitebanhang.repository.OrderDetailsRepo;
import com.websitebanhang.service.OrderDetailService;

@Service
public class OrderDetailImpl implements OrderDetailService {

	@Autowired
	private OrderDetailsRepo repo;

	@Transactional(value = TxType.REQUIRED)
	@Override
	public void insert(CartDetailDto cartDetailDto) {
		repo.insert(cartDetailDto);
	}

}
