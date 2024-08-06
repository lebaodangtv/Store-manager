package com.websitebanhang.service.impl;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.websitebanhang.constant.*;
import com.websitebanhang.dto.ProductsDto;
import com.websitebanhang.enums.CheckEmpty;
import com.websitebanhang.mapper.ProductsMapper;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.websitebanhang.entitys.Products;
import com.websitebanhang.repository.ProductsRepo;
import com.websitebanhang.service.ProductsService;

@Service
public class ProductsServiceImpl implements ProductsService {

	@Autowired
	private ProductsRepo repo;

	@Autowired
	private ProductsMapper productsMapper;


//    @Autowired
//    private ProductsMapper productsMapper;

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
	public Products findByName(String name) {
		return repo.findByName(name);
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
	@Transactional(rollbackOn = {Exception.class, Throwable.class})
	public void deleteProduct(String name) {
		repo.deleteProduct(name);
	}

	@Override
	public ApiResponse productType(Integer pageSize, Integer Number) {
		PageRequest page = PageRequest.of(pageSize, Number);
		Page<Object[]> request = repo.productType(page);
		List<ProductsDto> products = request.stream().map(x -> {
            ProductsDto products1 = new ProductsDto();
            products1.setProductName(CheckEmpty.checkStr(x[0]));
            products1.setPrice(CheckEmpty.checkDouble(x[1]));
            products1.setTypeName(CheckEmpty.checkStr(x[2]));
            return products1;
        }).collect(Collectors.toList());
		return ApiResponse.builder().message(Message.SUCCESS_QUERY)
				.code(200)
				.data(PageResponse.builder().
						items(Collections.singletonList(products)).
						pageNumber(request.getNumber()).
						pageSize((long) request.getSize()).
						limit((int) request.getTotalElements()).
						build())
				.build();
	}

	@Override
	public ResponseEntity<ByteArrayResource> export(String typeFile) throws Exception{
		List<Products> products = repo.findAll();
		List<ProductsDto> dto = productsMapper.toDTO(products);
		JRDataSource dataSource = new JRBeanCollectionDataSource(dto);
		// Tải và biên dịch template báo cáo
		InputStream inputStream = new ClassPathResource("export/DonHang.jrxml").getInputStream();
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("report", "Danh sách sản phẩm");
		// Điền dữ liệu vào báo cáo
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		// Xuất báo cáo ra định dạng PDF
		return  RequestFile.responseFile(typeFile,jasperPrint);
	}

}

