package com.example.villion_product_service.controller;

import com.example.villion_product_service.domain.request.RequestAddProduct;
import com.example.villion_product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product/")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public void test () {
        System.out.println("TEST---------------------------------");
    }


}
