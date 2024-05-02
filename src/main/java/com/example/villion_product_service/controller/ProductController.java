package com.example.villion_product_service.controller;

import com.example.villion_product_service.domain.request.RequestAddProduct;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ProductController {
    @PostMapping("addProduct/{userId}")
    public void addProduct(@PathVariable("userId") String userId, @RequestBody RequestAddProduct requestAddProduct) {

    }
}
