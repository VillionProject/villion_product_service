package com.example.villion_product_service.domain.dto;

import com.example.villion_product_service.domain.eunm.Category;
import com.example.villion_product_service.domain.eunm.ProductStatus;
import com.example.villion_product_service.domain.eunm.RentalMethod;
import com.example.villion_product_service.domain.eunm.RentalStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductDto {
        private Long productId;

//    private Long bookId;
        private Long ownerUserId; // 책주인(대여받는 사람)

        private String bookName;
        @Enumerated(EnumType.STRING)
        private Category category; // enum
        @Enumerated(EnumType.STRING)
        private ProductStatus productStatus; // enum
        @Enumerated(EnumType.STRING)
        private RentalStatus rentalStatus; // 대여 상태
        private Long stockQuantity;
        private Long rentalPrice;
        @Enumerated(EnumType.STRING)
        private RentalMethod rentalMethod; // enum
        private boolean popularity; // 인기제품이다 아니다.
        private String rentalLocation;
        private String description;
        private Boolean rentable;
        private Boolean purchasable;

}
