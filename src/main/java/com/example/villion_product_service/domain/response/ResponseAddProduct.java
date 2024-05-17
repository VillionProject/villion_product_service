package com.example.villion_product_service.domain.response;

import com.example.villion_product_service.domain.eunm.ProductStatus;
import com.example.villion_product_service.domain.eunm.RentalMethod;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.Date;

@Data
public class ResponseAddProduct {
    private Long productId;

    private Long bookId;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;
    private Date rentalPeriod;
    private Long RentalPrice;
    private RentalMethod rentalMethod;
}
