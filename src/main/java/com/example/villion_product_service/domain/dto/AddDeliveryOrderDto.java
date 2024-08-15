package com.example.villion_product_service.domain.dto;

import com.example.villion_product_service.domain.eunm.PaymentMethod;
import com.example.villion_product_service.domain.eunm.RentalMethod;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AddDeliveryOrderDto {
    private Long Id;

    private Long renterUserId; // 대여하는 사람

    // 배송 내역 정보
    private Long productId;
    private String userName;
    private Long phoneNumber;
    private String address;
    private String deliveryMemo;
    private LocalDate rentalStartDate; //  대여시작일
    private LocalDate rentalEndDate; // 대여마감일
    private Long shippingCost;
    private Long usedPoints;

    @Enumerated(EnumType.STRING)
    private RentalMethod rentalMethod; // enum

    private List<RentalProducts> orderList; // 주문 내역은 화면에서 받아서 보여주기


    // 결제 내역 정보
    private PaymentMethod paymentMethod;
}
