package com.example.villion_product_service.domain.entity;

import com.example.villion_product_service.domain.eunm.Category;
import com.example.villion_product_service.domain.eunm.RentalMethod;
import com.example.villion_product_service.domain.eunm.ProductStatus;
import com.example.villion_product_service.domain.eunm.RentalStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String bookName;
    @Enumerated(EnumType.STRING)
    private Category category; // enum
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus; // enum
    @Enumerated(EnumType.STRING)
    private RentalStatus rentalStatus; // 대여 상태
    private Long rentalQuantity;
    private LocalDate rentalPeriod; // 대여 가능 기간
    private Long rentalPrice;
    @Enumerated(EnumType.STRING)
    private RentalMethod rentalMethod; // enum
    private boolean popularity; // 인기제품이다 아니다.
    private String rentalLocation;
    private String description;
    private Boolean rentable;
    private Boolean purchasable;

    private Long userId;

}
