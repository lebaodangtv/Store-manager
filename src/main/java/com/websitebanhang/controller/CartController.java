package com.websitebanhang.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.websitebanhang.dto.CartDto;
import com.websitebanhang.entitys.ProductTypes;
import com.websitebanhang.entitys.Products;
import com.websitebanhang.service.CartService;
import com.websitebanhang.service.ProductTypeService;
import com.websitebanhang.service.ProductsService;
import com.websitebanhang.util.SessionUtil;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	ProductsService productService;
	
	@Autowired 
	private ProductTypeService productTypeService;

	@GetMapping("")
	public String doGetViewCart(Model model, @PathVariable("id") Optional<Long> id) {
		if(id.isPresent()) {
			List<Products> products = productService.productsTypeID(id.get());
			model.addAttribute("products", products);
			List<ProductTypes> productType = productTypeService.fillAll();
			model.addAttribute("productType", productType);
		}else {
			List<Products> products = productService.findAll();
			model.addAttribute("products", products);
			List<ProductTypes> productType = productTypeService.fillAll();
			model.addAttribute("productType", productType);
		}
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
