package com.example.villion_product_service.domain.dto;

import com.example.villion_product_service.domain.eunm.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddRentedDeliveryOrderDto {
    private Long Id;

    private Long ownerUserId; // 책주인(대여받는 사람)(defalt)
    private Long renterUserId; // 대여하는 사람

    // 배송 내역 정보
    private Long productId;
//    private String userName;
//    private Long phoneNumber;
//    private String address;
//    private String deliveryMemo;
    private LocalDate rentalStartDate; //  대여 시작일
    private LocalDate rentalEndDate; // 대여 종료일
    private Long totalRentalQuantity;
//    private Long shippingCost;
    private Long totalRentalPrice;
//    private Long usedPoints;
    private List<OrderDto> orderList; // 주문 내역은 화면에서 받아서 보여주기


    // 결제 내역 정보
    private PaymentMethod paymentMethod;


    // 도서 정보
    private String bookName;
    @Enumerated(EnumType.STRING)
    private Category category; // enum
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus; // enum
    @Enumerated(EnumType.STRING)
    private RentalStatus rentalStatus; // 대여 상태
    private Long rentalQuantity;
    //    private LocalDate rentalPeriod; // 대여 가능 기간
    private Long rentalPrice;
    @Enumerated(EnumType.STRING)
    private RentalMethod rentalMethod; // enum
    private String rentalLocation;
}
