package com.websitebanhang.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.websitebanhang.entitys.Users;
import com.websitebanhang.service.UsersService;

@Controller
@RequestMapping("/admin/user")
public class UserController {
	
	@Autowired
	private UsersService userService;
	
	@GetMapping("")
	public String doGetIndex(Model model) {
		List<Users> users = userService.findAll();
		model.addAttribute("users",users);
		return "admin/user";
	}
	
}
