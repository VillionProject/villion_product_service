package com.example.villion_product_service.controller;

import com.example.villion_product_service.client.RequestCart;
import com.example.villion_product_service.domain.entity.BookEntity;
import com.example.villion_product_service.domain.request.RequestAddProduct;
import com.example.villion_product_service.service.BookService;
import com.example.villion_product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product/")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final BookService bookService;

//    @GetMapping("{/productId}")
//    public void getBooks(@PathVariable("productId") Long productId) {
//
//    }

//    @GetMapping("/{bookId}")
//    public RequestCart getBooks(@PathVariable("bookId") Long bookId) {
//        RequestCart book = bookService.getBook(bookId);
//        return book;
//    }

//    @PostMapping("/{userId}")
//    public void addProduct(@PathVariable Long userId, @RequestBody RequestAddProduct requestAddProduct) {
//        productService.addProduct(userId, requestAddProduct);
//    }

}
