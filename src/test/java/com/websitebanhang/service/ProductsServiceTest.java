package com.websitebanhang.service;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.websitebanhang.entitys.ProductTypes;
import com.websitebanhang.entitys.Products;
import com.websitebanhang.entitys.UnitTypes;
import com.websitebanhang.repository.ProductsRepo;
import com.websitebanhang.service.impl.ProductsServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ProductsServiceTest {
	
	@InjectMocks
	private ProductsServiceImpl productsService;
	
	@Mock
	private ProductsRepo repo;
	
	@Test
	public void test_findAll_Success() {
		List<Products> expected = new ArrayList<>();
		expected.add(new Products(1L, "products1", 1, 1D, "", "","", Boolean.FALSE, new ProductTypes(), new UnitTypes()));
		expected.add(new Products(2L, "products2", 2, 2D, "", "","", Boolean.FALSE, new ProductTypes(), new UnitTypes()));
		// thiết lập database giả
		when(repo.findByIsDeletedAndQuantityGreaterThan(Boolean.FALSE, 0)).thenReturn(expected);
		// test
		List<Products> actual = new ArrayList<>();

		try {
			actual = productsService.findAll();
			assertEquals(expected.size(), actual.size()); // so sánh 
		} catch (Exception e) {
			fail("Should not throws exception");
		}
	}
	

	// @Mock dùng class
	// @InjectMocks class bị phụ thuộc class khác @auto

}
