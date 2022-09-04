package com.websitebanhang.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.websitebanhang.entitys.ProductTypes;
import com.websitebanhang.entitys.Products;
import com.websitebanhang.entitys.UnitTypes;
import com.websitebanhang.service.ProductTypeService;
import com.websitebanhang.service.ProductsService;
import com.websitebanhang.service.UnitTypeService;

@Controller
@RequestMapping("/admin/product")
public class ProductsController {
	
	@Autowired
	ProductsService productsService;
	@Autowired
	ProductTypeService productTypes;
	@Autowired
	UnitTypeService unitTypesSer;
	
	@GetMapping("")
	public String products(Model model) {
		List<Products> products = productsService.findAll();
		model.addAttribute("products",products);
		List<ProductTypes> productType = productTypes.fillAll();
		model.addAttribute("productTypes",productType);
		List<UnitTypes> unitType = unitTypesSer.findAll();
		model.addAttribute("unitTypes",unitType);
		model.addAttribute("userRequest", new Products());
		return "admin/product";
	}
	
	@PostMapping("/create")
	public String doPostProductRequest(@ModelAttribute("userRequest") Products product,
			RedirectAttributes redirectAttributes) {
		try {
			productsService.save(product);
			redirectAttributes.addFlashAttribute("succeedMessage", "Product" + product.getName() + "was create successfully");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMessage", "Cannot create product" + product.getName());
		}
		return "redirect:/admin/product";
	}
}
