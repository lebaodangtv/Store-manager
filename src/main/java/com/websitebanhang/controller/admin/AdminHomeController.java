package com.websitebanhang.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.websitebanhang.service.StatsService;


@Controller
@RequestMapping("/admin")
public class AdminHomeController {
	
	@Autowired
	private StatsService statsService;
	
	@GetMapping("")
	public String doGetIndex(Model model) {
		String chartData[][] = statsService.getTotalPriceLast6Months();
		model.addAttribute("chartData", chartData);
		return "admin/index";
	}
}
