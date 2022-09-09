package com.websitebanhang.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.websitebanhang.entitys.Orders;
import com.websitebanhang.service.OrdersService;

@Controller
@RequestMapping("/admin/order")
public class OrdersController {
	
	@Autowired
	OrdersService orderService;
	
	@GetMapping("")
	public String doGetOrder(Model model) {
		List<Orders> order = orderService.findAll();
		model.addAttribute("order",order);
		return "admin/orders";
	}
	
}
