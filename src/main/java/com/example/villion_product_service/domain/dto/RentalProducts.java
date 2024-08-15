package com.example.villion_product_service.domain.dto;

import lombok.Data;

@Data
public class RentalProducts {
    private Long productId;
    private String productName;
    private Long quantity;
    private Long price;
}
