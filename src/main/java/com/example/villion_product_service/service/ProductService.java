package com.example.villion_product_service.service;

import com.example.villion_product_service.domain.entity.BookEntity;
import com.example.villion_product_service.domain.entity.ProductEntity;
import com.example.villion_product_service.domain.response.ResponseAddProduct;
import com.example.villion_product_service.domain.request.RequestAddProduct;
import com.example.villion_product_service.repository.BookRepository;
import com.example.villion_product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;


    public void addProduct(Long userId, RequestAddProduct requestAddProduct) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ProductEntity productEntity = mapper.map(requestAddProduct, ProductEntity.class);
        productRepository.save(productEntity);
    }
}
