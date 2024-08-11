package com.microservice.admin.api;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
public class Report {

    private final ProductsService productsService;

    public Report(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/product")
    public ResponseEntity<ByteArrayResource> reportProduct(
            @RequestParam(value = "typeFile", required = false) String typeFile
    ) throws Exception {
        return productsService.export(typeFile);
    }
}
