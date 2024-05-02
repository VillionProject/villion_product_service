package com.example.villion_product_service.domain.entity;

import com.example.villion_product_service.domain.eunm.Category;
import com.example.villion_product_service.domain.eunm.RentalMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
    private LocalDate rentalPeriod;
    private Long rentalPrice;
    private RentalMethod rentalMethod; // enum
    private boolean popularity;
    private String rentalLocation;
    private String description;

}
