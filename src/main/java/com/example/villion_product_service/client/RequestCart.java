package com.example.villion_product_service.client;

import lombok.Data;

@Data
public class RequestCart {
    private Long productId;


//    #사용자
    private Long ownerUserId; // 임대하는 사람(defalt)
    private Long userId; // 임차하는 사람
    private Long rentalQuantity;
}
