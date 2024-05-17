package com.example.villion_product_service.domain.request;

import com.example.villion_product_service.domain.eunm.ProductStatus;
import com.example.villion_product_service.domain.eunm.RentalMethod;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalTime;

@Data
public class RequestAddProduct {
    private Long productId;

    private Long bookId;

    private String bookName;
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;
    private LocalTime rentalPeriod;
    private Long RentalPrice;
    private RentalMethod rentalMethod;
    private boolean popularity;  // 인기제품이다 아니다.
    private String RentalLocation;
    private String Description;
    private boolean Rentable;
    private boolean Purchasable;



}
