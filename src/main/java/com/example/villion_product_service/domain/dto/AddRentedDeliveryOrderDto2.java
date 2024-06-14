package com.example.villion_product_service.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRentedDeliveryOrderDto2 {
    private Long ownerUserId; // 책주인(대여받는 사람)(defalt)
    private Long renterUserId; // 대여하는 사람

}
