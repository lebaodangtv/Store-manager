package com.websitebanhang.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.websitebanhang.entitys.Products;
import com.websitebanhang.repository.ProductsRepo;
import com.websitebanhang.service.ProductsService;

@Service
public class ProductsServiceImpl implements ProductsService {

	@Autowired
	private ProductsRepo repo;

	@Override
	public List<Products> findAll() {
		return repo.findByIsDeletedAndQuantityGreaterThan(Boolean.FALSE, 0);
	}

	@Override
	public Products findById(Long id) {
		Optional<Products> result = repo.findById(id);
		/*
		 * nếu như Optional có nhận 1 object trả về thì retuen lấy ra ko thì trả về null
		 */
		return result.isPresent() ? result.get() : null;
	}

	@Override
	public Products findBySlug(String slug) {
		return repo.findBySlug(slug);
	}
	
	/* sao này nếu thự hiện update quantity trong admin
	 * mà bị bug thì nhớ bỏ value = TxType.REQUIRED đi
	 */
	@Transactional(value = TxType.REQUIRED)
	@Override
	public void updateQuantity(Integer newQuatity, Long id) {
		repo.updateQuantity(newQuatity, id);
	}

	@Override
	public List<Products> productsTypeID(Long typeId) {
		return repo.getAllType(typeId);
	}

	@Override
	public Page<Products> findAll(int pageSize, int pageNumber) throws Exception {
		if(pageNumber >= 1) {
			return repo.findByIsDeletedAndQuantityGreaterThan(Boolean.FALSE, 0, PageRequest.of(pageNumber - 1 , pageSize));
		} else {
			throw new Exception("Page number must be greater than 0");
		}
	}

	@Override
	@Transactional(rollbackOn = {Exception.class, Throwable.class})
	public Products save(Products product) {
		product.setIsDeleted(Boolean.FALSE);
		return repo.saveAndFlush(product);
	}

	@Override
	@Transactional(rollbackOn = {Exception.class, Throwable.class})
	public void updateProduct(Products product) {
		if(ObjectUtils.isNotEmpty(product)) {
			repo.updateProduct(product.getProductTypes().getId(), product.getQuantity(), product.getPrice(), product.getUnitTypes().getId(),product.getImgUrl(), product.getDescription(), product.getSlug(), product.getName());
		}
	}

	@Override
	public Products findByName(String name) {
		return repo.findByName(name);
	}
}
