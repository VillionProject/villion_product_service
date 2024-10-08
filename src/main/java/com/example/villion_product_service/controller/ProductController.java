package com.example.villion_product_service.controller;

import com.example.villion_product_service.domain.dto.GetLibraryWithProductDto;
import com.example.villion_product_service.domain.entity.ProductEntity;
import com.example.villion_product_service.domain.eunm.Category;
import com.example.villion_product_service.service.BookService;
import com.example.villion_product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

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


    // 위치별 상품 조회
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

    // 도서 조회
    @GetMapping("/getProduct/{productId}")
    public ProductEntity getProduct(@PathVariable Long productId) {
        return productService.getProduct(productId);
    }



    // 제품 상세 보기 - 이 책과 같은 카테고리 도서 목록
    @GetMapping("/getProductsByCategory/{productId}")
    public List<ProductEntity> getProductsByCategory(@PathVariable Long productId) {
        return productService.getProductsByCategory(productId);
    }

    // 제품 상제 보기 - 이 책과 함께 주문한 도서
    // 거래 완료된 내역에서 같이 주문된  것 가져오기

    // 도서 검색
    @GetMapping("/searchProduct/{word}")
    public List<ProductEntity> searchProduct(@PathVariable String word) {
        return productService.searchProduct(word);
    }

    // 메인 - Villion이 추천하는 책 리스트
    @GetMapping("/suggestedCategory/{category}")
    public List<ProductEntity> suggestedCategory(@PathVariable String category) {
        Category suggestedCategory = Category.TRAVEL;
        return productService.suggestedCategory(suggestedCategory);
    }


    // 카테고리별 도서 조회(MBTI테스트에서 활용)
    @GetMapping("/getCategory/{category}")
    public List<ProductEntity> searchProduct(@PathVariable Category category) {
        return productService.getCategory(category);
    }
}
