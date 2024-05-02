package com.example.villion_product_service.domain.entity;

import com.example.villion_product_service.domain.eunm.Category;
import com.example.villion_product_service.domain.eunm.RentalMethod;
import com.example.villion_product_service.domain.eunm.Status;
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

    private Category category; // enum
    private Status status; // enum
    private LocalDate rentalPeriod; // 대여 가능 기간
    private Long rentalPrice;
    private RentalMethod rentalMethod; // enum
    private boolean popularity;
    private String rentalLocation;
    private String description;
    private Boolean rentable;
    private Boolean purchasable;

}
