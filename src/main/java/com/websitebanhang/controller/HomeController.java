package com.websitebanhang.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.websitebanhang.constant.SessionConstant;
import com.websitebanhang.constant.UsersRequestConstant;
import com.websitebanhang.entitys.Products;
import com.websitebanhang.entitys.Users;
import com.websitebanhang.service.ProductsService;
import com.websitebanhang.service.UsersService;

@Controller
public class HomeController {
	
	@Autowired
	ProductsService productService;
	 
	@Autowired
	private UsersService usersService;
	
	@GetMapping(value = {"/",""})
	public String doGetRedirectIndex(){
		return "redirect:/index";
	}
	
	@GetMapping("/index")
	public String doGetIndex(Model model) {
		List<Products> products = productService.findAll();
		model.addAttribute("products", products);
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
	public String doGetRegister(){
		return "user/register";
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
