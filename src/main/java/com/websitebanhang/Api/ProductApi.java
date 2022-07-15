package com.websitebanhang.Api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.websitebanhang.entitys.Products;
import com.websitebanhang.service.ProductsService;

// trả ra dạng dữ liệu JSON
@RestController
// endpoint nơi cung cấp các API liên quan đến product
@RequestMapping("/v1/api/products")
public class ProductApi {
	
	@Autowired
	private ProductsService productsService;
	
	// localhost:8080/v1/api/products
	@GetMapping("")
	public ResponseEntity<?> doGetAll(){
		List<Products> products = productsService.findAll();
		return ResponseEntity.ok(products);
	}
	
	//localhost:8080/v1/api/products/slug/{...}
	@GetMapping("/slug/{slug}")
	public ResponseEntity<?> doGetBySlug(@PathVariable("slug") String slug){
		Products product = productsService.findBySlug(slug);
		return ResponseEntity.ok(product);
	}
}
