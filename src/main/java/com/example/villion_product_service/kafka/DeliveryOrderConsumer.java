package com.example.villion_product_service.kafka;

import com.example.villion_product_service.domain.dto.AddDeliveryOrderDto;
import com.example.villion_product_service.domain.dto.AddRentedDeliveryOrderDto;
import com.example.villion_product_service.domain.dto.AddRentedDeliveryOrderDto2;
import com.example.villion_product_service.domain.dto.OrderDto;
import com.example.villion_product_service.domain.entity.ProductEntity;
import com.example.villion_product_service.domain.eunm.RentalStatus;
import com.example.villion_product_service.repository.ProductCountRepository;
import com.example.villion_product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeliveryOrderConsumer {
    private final ProductRepository productRepository;
    private final addRentedDeliveryOrderProducer addRentedDeliveryOrderProducer;
    private final TestProducer testProducer;
    private final ProductCountRepository productCountRepository;

    @KafkaListener(topics = TopicConfig.addDeliveryOrder)
    public void addDeliveryOrderResult(AddDeliveryOrderDto addDeliveryOrderDto) {

        for (OrderDto orderDto : addDeliveryOrderDto.getOrderList()) {

            Long productId = orderDto.getProductId();
            ProductEntity byProductId = productRepository.findByProductId(productId);

            // 주문량이 재고량 못 넘어가게 제한
            Long currentStock = byProductId.getStockQuantity(); // 현재 재고량
            Long orderedQuantity = orderDto.getQuantity(); // 주문량
            Long resultStock = currentStock - orderedQuantity; // 현재 재고량 - 주문량

            // 주문 수량 초기화
            productCountRepository.resetOrderCount(String.valueOf(productId));

            // 주문량 증가 및 결과 확인
            Long orderCount = productCountRepository.increment(String.valueOf(productId), orderedQuantity);

            // orderCount가 몇까지 증가했는지 확인
            if (orderCount != null && orderCount <= currentStock) {
                // 재고량 업데이트
                // 등록된 상품 재고량도 주문량만큼 -되고 "저장"이되어야 함..
                byProductId.setStockQuantity(resultStock);

                // 재고량이 0이면 UNAVAILABLE, 남아있으면 AVAILABLE, 하지만 rental-service 넘어가는건 RENTED
                if(resultStock == 0) {
                    byProductId.setRentalStatus(RentalStatus.UNAVAILABLE); // 대여완료로 변경
                } else {
                    byProductId.setRentalStatus(RentalStatus.AVAILABLE);
                }
                productRepository.save(byProductId);



                // rental-service : 대여원장으로 넘겨줌
                AddRentedDeliveryOrderDto build = AddRentedDeliveryOrderDto.builder()
                        .productId(byProductId.getProductId())
                        .ownerUserId(byProductId.getOwnerUserId())

                        .renterUserId(addDeliveryOrderDto.getRenterUserId())
                        .rentalStartDate(addDeliveryOrderDto.getRentalStartDate())
                        .rentalEndDate(addDeliveryOrderDto.getRentalEndDate())
                        .totalRentalQuantity(addDeliveryOrderDto.getTotalRentalQuantity()) // TODO 주문내역에서 상품별 수량*금액 더해서 보여주기
                        .totalRentalPrice(addDeliveryOrderDto.getTotalRentalPrice())
                        .orderList(addDeliveryOrderDto.getOrderList())
                        .paymentMethod(addDeliveryOrderDto.getPaymentMethod())

                        .bookName(byProductId.getBookName())
                        .category(byProductId.getCategory())
                        .productStatus(byProductId.getProductStatus())
                        .rentalStatus(RentalStatus.RENTED)
                        .rentalPrice(byProductId.getRentalPrice())
                        .rentalMethod(byProductId.getRentalMethod())
                        .rentalLocation(byProductId.getRentalLocation())
                        .build();

                addRentedDeliveryOrderProducer.send(TopicConfig.addRentedDeliveryOrder, build);
//            addRentedDeliveryOrderProducer.send(TopicConfig.addRentedDeliveryOrder, map);

            } else {
                log.error("Ordered quantity exceeds available stock for productId: " + productId);
            }

        }

    }
}
