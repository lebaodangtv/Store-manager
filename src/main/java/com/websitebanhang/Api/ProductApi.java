package com.websitebanhang.Api;

import com.websitebanhang.constant.ApiResponse;
import com.websitebanhang.entitys.Products;
import com.websitebanhang.service.ProductsService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
// endpoint nơi cung cấp các API liên quan đến product
@RequestMapping("/v1/api/products")
public class ProductApi {

	@Autowired
	private ProductsService productsService;

	// localhost:8080/v1/api/products
	@GetMapping("")
	public ResponseEntity<?> doGetAll() {
		List<Products> products = productsService.findAll();
		return ResponseEntity.ok(products);
	}

	// localhost:8080/v1/api/products/slug/{...}
	@GetMapping("/slug/{slug}")
	public ResponseEntity<?> doGetBySlug(@PathVariable("slug") String slug) {
		Products product = productsService.findBySlug(slug);
		if (ObjectUtils.isEmpty(product)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // erorr 400
		}
		return ResponseEntity.ok(product);
	}

	// localhost:8080/v1/api/products/find?id={..}
	@GetMapping("/find")
	public ResponseEntity<?> doGetById(@RequestParam("id") Long id) {
		Products product = productsService.findById(id);
		if (ObjectUtils.isEmpty(product)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); // erorr 404
		}
		return ResponseEntity.ok(product);
	}

	@GetMapping("/product-type")
	public ApiResponse productType(
			@RequestParam(value = "pageSize" ,defaultValue = "0") Integer pageSize,
			@RequestParam(value = "pageNumber" ,defaultValue = "10") Integer pageNumber
	){
		return productsService.productType(pageSize,pageNumber);
	}
}
