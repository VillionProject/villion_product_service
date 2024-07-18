package com.example.villion_product_service.domain.dto;

import com.example.villion_product_service.domain.eunm.Category;
import com.example.villion_product_service.domain.eunm.ProductStatus;
import com.example.villion_product_service.domain.eunm.RentalMethod;
import com.example.villion_product_service.domain.eunm.RentalStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetLibraryWithProductDto {
    private Long productId;

    private Long ownerUserId;
    private String LibraryName; // ProductEntity에서 도서관 이름만 추가된거임..(ownerUserId의 도서관이름)


    private String bookName;
    @Enumerated(EnumType.STRING)
    private Category category; // enum
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus; // enum
    @Enumerated(EnumType.STRING)
    private RentalStatus rentalStatus; // 대여 상태
    private Long stockQuantity;
    //    private LocalDate rentalPeriod; // 대여 가능 기간
    private Long rentalPrice;
    @Enumerated(EnumType.STRING)
    private RentalMethod rentalMethod; // enum
    private String rentalLocation;
    private String description;
    private Boolean rentable;
    private Boolean purchasable;


}
