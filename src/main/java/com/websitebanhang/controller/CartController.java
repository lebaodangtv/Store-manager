package com.websitebanhang.controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.websitebanhang.dto.CartDto;
import com.websitebanhang.service.CartService;
import com.websitebanhang.service.ProductsService;
import com.websitebanhang.util.SessionUtil;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	ProductsService productService;
	

	@GetMapping("")
	public String doGetViewCart() {
		return "user/cart";
	}

	// /cart/update?productId=...&quantity=...&isReplace..
	@GetMapping("/update")
	public String doGetUpdate(@RequestParam("productId") Long productId,
			@RequestParam("quantity") Integer quantity, @RequestParam("isReplace") Boolean isREplace,
			HttpSession session) {
		CartDto currentCart = SessionUtil.getCurrentCart(session);
		cartService.updateCart(currentCart, productId, quantity, isREplace);
		return "user/cart::#viewCartFragment";
	}

}
