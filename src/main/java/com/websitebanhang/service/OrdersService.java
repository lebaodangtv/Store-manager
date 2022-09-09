package com.websitebanhang.service;

import java.util.List;

import com.websitebanhang.entitys.Orders;

public interface OrdersService {
	Orders insert(Orders orders);
	List<Orders> findAll();
}
