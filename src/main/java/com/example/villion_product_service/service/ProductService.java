package com.example.villion_product_service.service;

import com.example.villion_product_service.domain.entity.BookEntity;
import com.example.villion_product_service.domain.response.ResponseAddProduct;
import com.example.villion_product_service.domain.request.RequestAddProduct;
import com.example.villion_product_service.repository.BookRepository;
import com.example.villion_product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final BookRepository bookRepository;


}
