package com.example.villion_product_service.domain.dto;

import com.example.villion_product_service.domain.eunm.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddRentedDeliveryOrderDto {
//    private Long Id;

    private Long ownerUserId; // 책주인(대여받는 사람)(defalt)
    private Long renterUserId; // 대여하는 사람

    //    #제품
    private Long productId;
    private LocalDate rentalStartDate; // 대여 시작일
    private LocalDate rentalEndDate;   // 대여 종료일
    private Long totalRentalQuantity;
    private Long totalRentalPrice;

    // 결제 내역 정보
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private Long usedPoints;
    private Long shippingCost;

    // 주문 정보
    private String userName;
    private Long phoneNumber;
    private String address;
    private String deliveryMemo;

    @Enumerated(EnumType.STRING)
    private RentalStatus rentalStatus; // 대여 상태
    @Enumerated(EnumType.STRING)
    private RentalMethod rentalMethod; // enum
//    private LocalDateTime orderedAt; // 주문일

    // 주문 목록
    private List<RentalProducts> rentalProducts;
}
