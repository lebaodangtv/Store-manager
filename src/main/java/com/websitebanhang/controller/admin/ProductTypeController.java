package com.websitebanhang.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.websitebanhang.entitys.ProductTypes;
import com.websitebanhang.service.ProductTypeService;

@Controller
@RequestMapping("/admin/producttype")
public class ProductTypeController {
	
	@Autowired
	ProductTypeService productTypeService;
	
	@GetMapping("")
	public String doGetProduct(Model model) {
		List<ProductTypes> productTypes = productTypeService.fillAll(); 
		model.addAttribute("productTypes",productTypes);
		model.addAttribute("productTypeRequest",new ProductTypes());
		return "admin/productType";
	}

}
