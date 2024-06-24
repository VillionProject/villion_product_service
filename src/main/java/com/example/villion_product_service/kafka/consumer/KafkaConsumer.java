package com.example.villion_product_service.kafka.consumer;

import com.example.villion_product_service.domain.dto.ProductDto;
import com.example.villion_product_service.domain.entity.ProductEntity;
import com.example.villion_product_service.kafka.config.TopicConfig;
import com.example.villion_product_service.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {
    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ModelMapper modelMapper = new ModelMapper();


    @KafkaListener(topics = TopicConfig.addProduct, groupId = "group_2")
    public void addProductResult(ProductDto productDto) {
        System.out.println(productDto);
        ModelMapper mapper = new ModelMapper();
        ProductEntity productEntity = mapper.map(productDto, ProductEntity.class);
        productRepository.save(productEntity);
    }





//    @KafkaListener(topics = "add-product-topic", groupId = "group_1")
//    public void saveProduct(String kafkaMessage) {
//        if (kafkaMessage == null || kafkaMessage.trim().isEmpty()) {
//            log.warn("Received empty message");
//            return;
//        }
//
//        try {
//            log.info("Kafka message: ->" + kafkaMessage);
//
//            Map<Object, Object> map = new HashMap<>();
//            ObjectMapper mapper = new ObjectMapper(); // 메시지를 직렬화해서 전달된다..ObjectMapper를 이용해 역직렬화해서 사용한다
//            // TODO 역직렬화
//            try {
//                map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
//            } catch (JsonProcessingException ex) {
//                ex.printStackTrace();
//            }
//
//            ModelMapper mapper1 = new ModelMapper();
//            ProductEntity productEntity = mapper1.map(map, ProductEntity.class);
//
//            // 데이터베이스에 저장
//            productRepository.save(productEntity);
//        } catch (Exception e) {
//            log.error("Error processing Kafka message", e);
//        }
//    }

}
