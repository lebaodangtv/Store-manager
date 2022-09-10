package com.websitebanhang.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.websitebanhang.entitys.OrderDetails;
import com.websitebanhang.entitys.Orders;
import com.websitebanhang.service.OrderDetailService;
import com.websitebanhang.service.OrdersService;

@Controller
@RequestMapping("/admin/order")
public class OrdersController {
	
	@Autowired
	OrdersService orderService;
	
	@Autowired
	OrderDetailService orderDetailService;
	
	@GetMapping("")
	public String doGetOrder(Model model) {
		List<Orders> order = orderService.findAll();
		model.addAttribute("order",order);
		return "admin/orders";
	}
	
	@GetMapping("/orderdetail")
	public String doGetOrderDetail(Model model, @RequestParam("orderDetailID") Long orderDetailID) {
		List<OrderDetails> orderdetail = orderDetailService.findByOrderID(orderDetailID);
		model.addAttribute("orderdetail",orderdetail);
		return "/admin/orders :: #example2";
	}
	
}
