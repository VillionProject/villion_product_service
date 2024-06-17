package com.example.villion_product_service.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductDto {
    private Long productId;
    private String productName;

    private Long rentalQuantity;
    private Long rentalPrice;

}
