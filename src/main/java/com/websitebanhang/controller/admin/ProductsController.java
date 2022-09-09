package com.websitebanhang.controller.admin;


import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		List<ProductTypes> productType = productTypes.findAll();
		model.addAttribute("productTypes",productType);
		List<UnitTypes> unitType = unitTypesSer.findAll();
		model.addAttribute("unitTypes",unitType);
		model.addAttribute("productRequest", new Products());
		return "admin/product";
	}
	
	// /admin/product/delete?name={...}
	@GetMapping("/delete")
	public String doGetDelete(@RequestParam("productname") String name,
			RedirectAttributes redirectAttributes) {
		try {
			productsService.deleteProduct(name);
			redirectAttributes.addFlashAttribute("succeedMessage"," Product " + name + " was deleted ");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMessage"," Cannot delete product " + name );
		}
		return "redirect:/admin/product";
	}
	
	@GetMapping("/edit")
	public String doGetEditProduct(@RequestParam("productname") String productName, Model model) {
		Products productRequest = productsService.findByName(productName);
		model.addAttribute("productRequest", productRequest);
		List<ProductTypes> productType = productTypes.findAll();
		model.addAttribute("productTypes",productType);
		List<UnitTypes> unitType = unitTypesSer.findAll();
		model.addAttribute("unitTypes",unitType);
		return "/admin/product :: #form";
	}
	
	@PostMapping("/edit")
	public String doPostEditProduct(@Valid @ModelAttribute("productRequest") Products productRequest,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
			redirectAttributes.addFlashAttribute("errorMessage","Product is not valid");
		}else {
			try {
				productsService.updateProduct(productRequest);
				redirectAttributes.addFlashAttribute("succeedMessage", " Product " + productRequest.getName() + " has been edited successfully ");
			} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("errorMessage", " Cannot update product " + productRequest.getName());
			}
		}
		return "redirect:/admin/product";
	}
	//admin/product/create
	@PostMapping("/create")
	public String doPostProductRequest(@ModelAttribute("productRequest") Products product,
			RedirectAttributes redirectAttributes) {
		try {
			productsService.save(product);
			redirectAttributes.addFlashAttribute("succeedMessage", " Product " + product.getName() + " was create successfully ");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMessage", " Cannot create product " + product.getName());
		}
		return "redirect:/admin/product";
	}
}
