package com.websitebanhang.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.websitebanhang.constant.SessionConstant;
import com.websitebanhang.constant.UsersRequestConstant;
import com.websitebanhang.entitys.ProductTypes;
import com.websitebanhang.entitys.Products;
import com.websitebanhang.entitys.Users;
import com.websitebanhang.service.ProductTypeService;
import com.websitebanhang.service.ProductsService;
import com.websitebanhang.service.UsersService;

@Controller
public class HomeController {
	
	@Autowired
	ProductsService productService;
	 
	@Autowired
	private UsersService usersService;
	
	@Autowired 
	private ProductTypeService productTypeService;
	
	private static final int MAX_SIZE = 6;
	
	
	@GetMapping(value = {"/",""})
	public String doGetRedirectIndex(){
		return "redirect:/index";
	}
	
	@GetMapping("/index")
	public String doGetIndex(@RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) {
		List<Products> products = new ArrayList<>();
		try {
			Page<Products> pageProducts = productService.findAll(MAX_SIZE, page);
			products = pageProducts.getContent();
			model.addAttribute("totalPages", pageProducts.getTotalPages());
			model.addAttribute("currentPage", page);
		} catch (Exception e) {
			products = productService.findAll();
		}
		model.addAttribute("products", products);
		List<ProductTypes> productType = productTypeService.findAll();
		model.addAttribute("productType", productType);
		return "user/index";
	}
	
	@GetMapping("/type/{id}")
	public String doGetType(Model model, @PathVariable("id") Optional<Long> id) {
		if(id.isPresent()) {
			List<Products> products = productService.productsTypeID(id.get());
			model.addAttribute("products", products);
			List<ProductTypes> productType = productTypeService.findAll();
			model.addAttribute("productType", productType);
		}else {
			List<Products> products = productService.findAll();
			model.addAttribute("products", products);
			List<ProductTypes> productType = productTypeService.findAll();
			model.addAttribute("productType", productType);
		}
		return "user/index";
	}
	
	
	@GetMapping("/login")
	public String doGetLogin(Model model) {
		// tạo ra 1 user rỗng để hứng data người dùng nhập vào
		model.addAttribute("usersRequest", new Users());
		return "user/login";
	}
	
	@GetMapping("/logout")
	public String doGetLogout(HttpSession session) {
		session.removeAttribute(SessionConstant.CURRENT_USER);
		return "redirect:/index"; 
		
	}
	
	@GetMapping("/register")
	public String doGetRegister(Model model){
		model.addAttribute("userRequest", new Users());
		return "user/register";
	}
	
	@PostMapping("/register")
	public String doPostRegister(@ModelAttribute("userRequest")Users userRequest, HttpSession session ) {
		try {
			Users userResponse = usersService.save(userRequest);
			// nếu userResponse có trả về data
			if (ObjectUtils.isNotEmpty(userResponse)) {
				session.setAttribute(SessionConstant.CURRENT_USER, userResponse);
				return "redirect:/index";
			} else {
				return "redirect:/register";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return "redirect:/register";
		}
	}
	
	@PostMapping("/login")
	public String doPostLogin(@ModelAttribute(UsersRequestConstant.USERREQUESTCONSTANT) Users usersRequest, HttpSession session) {
		Users usersResponse = usersService.doLogin(usersRequest);
		if (ObjectUtils.isNotEmpty(usersResponse)) {
			// trướt khi chuyển trang lưu user vào session
			session.setAttribute(SessionConstant.CURRENT_USER, usersResponse);
			return "redirect:/index";
		}
		return "redirect:/login";
	}
}
