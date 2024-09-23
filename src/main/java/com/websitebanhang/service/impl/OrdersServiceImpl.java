package com.websitebanhang.service.impl;

import java.util.List;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websitebanhang.entitys.Orders;
import com.websitebanhang.repository.OrdersRepo;
import com.websitebanhang.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired 
	private OrdersRepo repo;
	
	
	/* value = TxType.REQUIRED: hàm insert này chỉ đưuọc gọi trong 1 transactional
	 đã được mở trước đó. cartService là 1 transac đã mở.@Transac này chỉ là 1 
	 thành phần trong đó */
	
	@Transactional(value = Transactional.TxType.REQUIRED)
	@Override
	public Orders insert(Orders orders) {
		return repo.saveAndFlush(orders);
	}


	@Override
	public List<Orders> findAll() {
		return repo.findAll();
	}

}
