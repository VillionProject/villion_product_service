package com.example.villion_product_service.controller;

import com.example.villion_product_service.domain.dto.GetLibraryWithProductDto;
import com.example.villion_product_service.domain.entity.ProductEntity;
import com.example.villion_product_service.service.BookService;
import com.example.villion_product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("")
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


    // 상품 조회
    @GetMapping("/getProductsByLocation")
    public List<ProductEntity> getProductsByLocation() {
        return productService.getProductsByLocation();
    }

    // 제품 상세 보기
    @GetMapping("/getProductDetail/{productId}")
    public ProductEntity getProductDetail(@PathVariable Long productId) {
        return productService.getProductDetail(productId);
    }


    // 제품 상세 보기 - 이 책을 가지고 있는 직거래 도서관 보여주기
    @GetMapping("/getLibraryWithProduct/{productId}")
    public List<GetLibraryWithProductDto> getLibraryWithProduct(@PathVariable Long productId) {
        return productService.getLibraryWithProduct(productId);
    }

}
