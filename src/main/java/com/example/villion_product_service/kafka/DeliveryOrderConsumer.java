package com.example.villion_product_service.kafka;

import com.example.villion_product_service.domain.dto.AddDeliveryOrderDto;
import com.example.villion_product_service.domain.dto.AddRentedDeliveryOrderDto;
import com.example.villion_product_service.domain.dto.AddRentedDeliveryOrderDto2;
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

//        AddRentedDeliveryOrderDto2 build = new AddRentedDeliveryOrderDto2(1L, 2L);
//
//        System.out.println("유저에서 잘옴 ");
//        System.out.println(addDeliveryOrderDto);
//
//        addRentedDeliveryOrderProducer.send(TopicConfig.addRentedDeliveryOrderLast2, build);

        for (ProductEntity productEntity : addDeliveryOrderDto.getOrderList()) {

            Long productId = productEntity.getProductId();
            ProductEntity byProductId = productRepository.findByProductId(productId);
            byProductId.setRentalStatus(RentalStatus.RENTED); // 대여완료로 변경 TODO 등록 수량이 0되면 바뀌어야 하나..?
            productRepository.save(byProductId);

            // RentalStatus.RENTED로 바뀌면 AddDeliveryOrderDto 주문정보 + ownerUserId 책주인(대여받는 사람)데이터를 가지고 rental-service에 저장
//            ModelMapper mapper = new ModelMapper();
//            mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//            AddRentedDeliveryOrderDto map = mapper.map(byProductId, AddRentedDeliveryOrderDto.class);
//            map.setOwnerUserId(byProductId.getOwnerUserId());
//            map.setRenterUserId(addDeliveryOrderDto.getRenterUserId());

            AddRentedDeliveryOrderDto build = AddRentedDeliveryOrderDto.builder()
                    .productId(byProductId.getProductId())
                    .ownerUserId(byProductId.getOwnerUserId())

                    .renterUserId(addDeliveryOrderDto.getRenterUserId())
                    .rentalStartDate(addDeliveryOrderDto.getRentalStartDate())
                    .rentalEndDate(addDeliveryOrderDto.getRentalEndDate())
                    .totalRentalQuantity(addDeliveryOrderDto.getTotalRentalQuantity())
                    .totalRentalPrice(addDeliveryOrderDto.getTotalRentalPrice())
                    .orderList(addDeliveryOrderDto.getOrderList())
                    .paymentMethod(addDeliveryOrderDto.getPaymentMethod())

                    .bookName(byProductId.getBookName())
                    .category(byProductId.getCategory())
                    .productStatus(byProductId.getProductStatus())
                    .rentalStatus(byProductId.getRentalStatus())
                    .rentalPrice(byProductId.getRentalPrice())
                    .rentalMethod(byProductId.getRentalMethod())
                    .rentalLocation(byProductId.getRentalLocation())
                    .build();

            addRentedDeliveryOrderProducer.send(TopicConfig.addRentedDeliveryOrder, build);
//            addRentedDeliveryOrderProducer.send(TopicConfig.addRentedDeliveryOrder, map);


            // 주문량이 재고량 못 넘어가게 제한
            Long currentStock = byProductId.getRentalQuantity(); // 현재 재고량
            Long orderedQuantity = productEntity.getRentalQuantity();
            long resultStock = byProductId.getRentalQuantity() - orderedQuantity;
            // 주문 수량이 재고량을 초과하는지 확인
            if (orderedQuantity > currentStock) {
                log.error("Ordered quantity exceeds available stock for productId: " + productId);
                // Handle the error appropriately, e.g., send a message to a Kafka error topic
                // or throw an exception
                continue;  // Skip this product and continue with the next
            }


            // 재고량 설정
            productCountRepository.setOrderCount(String.valueOf(addDeliveryOrderDto.getProductId()), resultStock);
            // TODO 만약 제한수 넘어가면 어떻게 나옴? 에러뜨나

            // TODO 등록된 상품 재고량도 주문량만큼 -되고 "저장"이되어야 함..
            byProductId.setRentalQuantity(resultStock);
            
            // 주문량 +1
            productCountRepository.increment(String.valueOf(addDeliveryOrderDto.getProductId()));
        }

    }
}
