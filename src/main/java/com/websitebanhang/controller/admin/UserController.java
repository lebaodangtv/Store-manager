package com.websitebanhang.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		model.addAttribute("userRequest", new Users());
		return "admin/user";
	}
	
	// /admin/user/delete?username={...}
	@GetMapping("/delete")
	public String doGetDelete(@RequestParam("username") String username,
			RedirectAttributes redirectAttributes) {
		try {
			userService.deleteLogical(username);
			redirectAttributes.addFlashAttribute("succeedMessage","User" + username + "was deleted");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMessage","Cannot delete user" + username );
		}
		return "redirect:/admin/user";
	}
	
	@PostMapping("/create")
	public String doPostUserRequest(@ModelAttribute("userRequest") Users userRequest) {
		
	}
	
}
