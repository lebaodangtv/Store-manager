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
import com.websitebanhang.service.ProductTypeService;

@Controller
@RequestMapping("/admin/producttype")
public class ProductTypeController {
	
	@Autowired
	ProductTypeService productTypeService;
	
	@GetMapping("")
	public String doGetProductType(Model model) {
		List<ProductTypes> productTypes = productTypeService.findAll(); 
		model.addAttribute("productTypes",productTypes);
		model.addAttribute("productTypeRequest",new ProductTypes());
		return "admin/productType";
	}
	
	@GetMapping("/edit")
	public String doGetEditProductType(Model model, @RequestParam("productTypeName") String name) {
		ProductTypes productTypeRequest = productTypeService.findByName(name);
		model.addAttribute("productTypeRequest",productTypeRequest);
		return "/admin/productType :: #form ";
	}
	
	@GetMapping("/delete")
	public String doGetDelete(@RequestParam("productTypeName") String name,
			RedirectAttributes redirectAttributes){
		try {
			System.out.println(name);
			productTypeService.deleteProductType(name);
			redirectAttributes.addFlashAttribute("succeedMessage"," Product Type " + name + " was deleted ");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMessage"," Cannot delete product type " + name );
		}
		return "redirect:/admin/producttype";
	}
	
	@PostMapping("/edit")
	public String doPostEditProductType(@Valid @ModelAttribute("productTypeRequest") ProductTypes productTypeRequest,
			BindingResult bindingResult, RedirectAttributes redirectAttributes ) {
		if (bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
			redirectAttributes.addFlashAttribute("errorMessage"," ProductType is not valid ");
		}else {
			try {
				productTypeService.updateProductType(productTypeRequest);
				redirectAttributes.addFlashAttribute("succeedMessage", " Product Type " + productTypeRequest.getName() + " has been edited successfully ");
			} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("errorMessage", " Cannot update product Type " + productTypeRequest.getName());
			}
		}
		return "redirect:/admin/productType";
	}
	
	@PostMapping("/create")
	public String doPostProductTypeRequest(@ModelAttribute("productTypeRequest") ProductTypes productType,
			RedirectAttributes redirectAttributes) {
		try {
			productTypeService.createProductType(productType);
			redirectAttributes.addFlashAttribute("succeedMessage", " Product Type " + productType.getName() + " was create successfully ");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMessage", " Cannot create product Type " + productType.getName());
		}
		return "redirect:/admin/producttype";
	}

}
