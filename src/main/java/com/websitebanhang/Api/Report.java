package com.websitebanhang.Api;

import com.websitebanhang.service.ProductsService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
public class Report {

    private final ProductsService productsService;

    public Report(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/product")
    public ResponseEntity<ByteArrayResource> reportProduct() throws Exception {
        return productsService.export();
    }
}
