package com.example.villion_product_service.controller;

import com.example.villion_product_service.service.BookService;
import com.example.villion_product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/book/")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/{bookId}")
    public void getBooks(@PathVariable("bookId") Long bookId) {

    }


}
