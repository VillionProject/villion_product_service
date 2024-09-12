package com.example.villion_product_service.kafka.consumer;

import com.example.villion_product_service.domain.dto.AddDeliveryOrderDto;
import com.example.villion_product_service.domain.dto.AddRentedDeliveryOrderDto;
import com.example.villion_product_service.domain.dto.RentalProducts;
import com.example.villion_product_service.domain.entity.ProductEntity;
import com.example.villion_product_service.domain.eunm.RentalStatus;
import com.example.villion_product_service.kafka.producer.AddRentedDeliveryOrderProducer;
import com.example.villion_product_service.kafka.config.TopicConfig;
import com.example.villion_product_service.repository.ProductCountRepository;
import com.example.villion_product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeliveryOrderConsumer {
    private final ProductRepository productRepository;
    private final AddRentedDeliveryOrderProducer addRentedDeliveryOrderProducer;
    private final ProductCountRepository productCountRepository;

    @KafkaListener(topics = TopicConfig.addDeliveryOrder)
    public void addDeliveryOrderResult(AddDeliveryOrderDto addDeliveryOrderDto) {
        Long totalRentalPrice = 0L;
        Long totalRentalQuantity = 0L;
        boolean hasRentedStatus = false; // RENTED 상태를 확인하기 위한 플래그
        ProductEntity byProductId = new ProductEntity();


        for (RentalProducts orderDto : addDeliveryOrderDto.getOrderList()) {

            Long productId = orderDto.getProductId();
            byProductId = productRepository.findByProductId(productId);

            // 주문량이 재고량 못 넘어가게 제한
            Long currentStock = byProductId.getStockQuantity(); // 현재 재고량
            Long orderedQuantity = orderDto.getQuantity(); // 주문량
            long resultStock = currentStock - orderedQuantity; // 현재 재고량 - 주문량

            // 주문 수량 초기화
            productCountRepository.resetOrderCount(String.valueOf(productId));

            // 주문량 증가 및 결과 확인
            Long orderCount = productCountRepository.increment(String.valueOf(productId), orderedQuantity);

            // orderCount가 몇까지 증가했는지 확인
            // 주문량(orderCount)이 재고(currentStock)를 초과하지 않았을 경우
            if (orderCount != null && orderCount <= currentStock && currentStock != 0) {
                // 재고량 업데이트
                // 등록된 상품 재고량도 주문량만큼 -되고 "저장"이되어야 함..
                byProductId.setStockQuantity(resultStock);

                // 재고량이 0이면 UNAVAILABLE, 남아있으면 AVAILABLE, 하지만 rental-service 넘어가는건 RENTED
                if(resultStock == 0) {
                    byProductId.setRentalStatus(RentalStatus.UNAVAILABLE);
                } else {
                    byProductId.setRentalStatus(RentalStatus.AVAILABLE);
                }

                if(addDeliveryOrderDto.getRentalEndDate() == null) { // 대여마감일에 날짜가 null이면, 구매완료
                    byProductId.setRentalStatus(RentalStatus.PURCHASED);
                } else {
                    // RENTED 상태로 설정
                    byProductId.setRentalStatus(RentalStatus.RENTED);
                }

                hasRentedStatus = true; // RENTED 상태 플래그 설정
                productRepository.save(byProductId);

                // 누적 가격과 수량 계산
                totalRentalPrice += orderedQuantity * byProductId.getRentalPrice();
                totalRentalQuantity += orderedQuantity;




                // RentalStatus.RENTED로 되는 경우
                // rental-service : 대여원장으로 넘겨줌
                // TODO 주문번호를 만들어서 넘겨줘야 함..주문번호로 mapping? nono 아니, rentalId가 생기기 때문에 주문번호 필요없어




//                AddRentedDeliveryOrderDto build = AddRentedDeliveryOrderDto.builder()
//                        .ownerUserId(byProductId.getOwnerUserId())
//                        .renterUserId(addDeliveryOrderDto.getRenterUserId())
//
//                        .productId(byProductId.getProductId())
//                        .rentalStartDate(addDeliveryOrderDto.getRentalStartDate())
//                        .rentalEndDate(addDeliveryOrderDto.getRentalEndDate())
//                        .totalRentalQuantity(TotalQuantity)
//                        .totalRentalPrice(TotalPrice)
//
//
//
//                        .rentalMethod(byProductId.getRentalMethod())
//
//                        .paymentMethod(addDeliveryOrderDto.getPaymentMethod())
//
//
//
//
//                        .rentalProducts(addDeliveryOrderDto.getOrderList())
//
//                        .build();



//                AddRentedDeliveryOrderDto build = AddRentedDeliveryOrderDto.builder()
//                        .productId(byProductId.getProductId())
//                        .ownerUserId(byProductId.getOwnerUserId())
//
//                        .renterUserId(addDeliveryOrderDto.getRenterUserId())
//                        .rentalStartDate(addDeliveryOrderDto.getRentalStartDate())
//                        .rentalEndDate(addDeliveryOrderDto.getRentalEndDate())
//                        .paymentMethod(addDeliveryOrderDto.getPaymentMethod())
//
//                        .bookName(byProductId.getBookName())
//                        .category(byProductId.getCategory())
//                        .productStatus(byProductId.getProductStatus())
//                        .rentalStatus(RentalStatus.RENTED)
//                        .rentalQuantity(orderDto.getQuantity())
//                        .rentalPrice(byProductId.getRentalPrice())
//                        .rentalMethod(byProductId.getRentalMethod())
//                        .rentalLocation(byProductId.getRentalLocation())
//
//                        .orderList(addDeliveryOrderDto.getOrderList())
//                        .build();



//                addRentedDeliveryOrderProducer.send(TopicConfig.addRentedDeliveryOrder, build);

            } else { // 주문량(orderCount)이 재고(currentStock)를 초과했을 경우
//                log.error("Ordered quantity exceeds available stock for productId: " + productId);
                throw new IllegalStateException("Ordered quantity exceeds available stock for productId: " + productId);
            }
        }
        // 반복문이 끝난 후, AddRentedDeliveryOrderDto 생성

        // RentalStatus.RENTED로 되는 경우
        // rental-service : 대여원장으로 넘겨줌
        if(hasRentedStatus) {

            AddRentedDeliveryOrderDto build = AddRentedDeliveryOrderDto.builder()
                    .ownerUserId(byProductId.getOwnerUserId())
                    .renterUserId(addDeliveryOrderDto.getRenterUserId())

                    .productId(byProductId.getProductId())
                    .rentalStartDate(addDeliveryOrderDto.getRentalStartDate())
                    .rentalEndDate(addDeliveryOrderDto.getRentalEndDate())
                    .totalRentalQuantity(totalRentalQuantity)
                    .totalRentalPrice(totalRentalPrice)

                    .paymentMethod(addDeliveryOrderDto.getPaymentMethod())
                    .usedPoints(addDeliveryOrderDto.getUsedPoints())
                    .shippingCost(addDeliveryOrderDto.getShippingCost())

                    .userName(addDeliveryOrderDto.getUserName())
                    .phoneNumber(addDeliveryOrderDto.getPhoneNumber())
                    .address(addDeliveryOrderDto.getAddress())
                    .deliveryMemo(addDeliveryOrderDto.getDeliveryMemo())


                    .rentalStatus(byProductId.getRentalStatus())
                    .rentalMethod(addDeliveryOrderDto.getRentalMethod())
//                    .orderedAt() // 최종대여가 완료되고 나서 rental-service에서 작성
                    .rentalProducts(addDeliveryOrderDto.getOrderList()) // TODO

                    .build();


            addRentedDeliveryOrderProducer.send(TopicConfig.addRentedDeliveryOrder, build);

        }
    }
}
