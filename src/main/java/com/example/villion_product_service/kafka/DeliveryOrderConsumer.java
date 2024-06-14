package com.example.villion_product_service.kafka;

import com.example.villion_product_service.domain.dto.AddDeliveryOrderDto;
import com.example.villion_product_service.domain.dto.AddRentedDeliveryOrderDto;
import com.example.villion_product_service.domain.dto.AddRentedDeliveryOrderDto2;
import com.example.villion_product_service.domain.entity.ProductEntity;
import com.example.villion_product_service.domain.eunm.RentalStatus;
import com.example.villion_product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeliveryOrderConsumer {
    private final ProductRepository productRepository;
    private final addRentedDeliveryOrderProducer addRentedDeliveryOrderProducer;

    @KafkaListener(topics = TopicConfig.addDeliveryOrder)
    public void addDeliveryOrderResult(AddDeliveryOrderDto addDeliveryOrderDto) {

        AddRentedDeliveryOrderDto2 build = AddRentedDeliveryOrderDto2.builder()
                .ownerUserId(1L)
                .renterUserId(2L)
                .build();

        System.out.println("유저에서 잘옴 ");


        addRentedDeliveryOrderProducer.send(TopicConfig.addRentedDeliveryOrderLast2, build);




//        for (ProductEntity productEntity : addDeliveryOrderDto.getOrderList()) {
//
//            Long productId = productEntity.getProductId();
//            ProductEntity byProductId = productRepository.findByProductId(productId);
//            byProductId.setRentalStatus(RentalStatus.RENTED); // 대여완료로 변경
//            productRepository.save(byProductId);
//
//            // RentalStatus.RENTED로 바뀌면 AddDeliveryOrderDto 주문정보 + ownerUserId 책주인(대여받는 사람)데이터를 가지고 rental-service에 저장
////            ModelMapper mapper = new ModelMapper();
////            AddRentedDeliveryOrderDto map = mapper.map(byProductId, AddRentedDeliveryOrderDto.class);
////            map.setOwnerUserId(byProductId.getOwnerUserId());
//
//            AddRentedDeliveryOrderDto build = AddRentedDeliveryOrderDto.builder()
//                    .ownerUserId(byProductId.getOwnerUserId())
//                    .renterUserId(addDeliveryOrderDto.getRenterUserId())
//                    .build();
//
//            addRentedDeliveryOrderProducer.send(TopicConfig.addRentedDeliveryOrder, build);
//        }



    }
}
