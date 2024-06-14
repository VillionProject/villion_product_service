package com.example.villion_product_service.kafka;

import com.example.villion_product_service.domain.dto.AddRentedDeliveryOrderDto;
import com.example.villion_product_service.domain.dto.AddRentedDeliveryOrderDto2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class addRentedDeliveryOrderProducer {

    private final KafkaTemplate<String, AddRentedDeliveryOrderDto2> kafkaTemplate;

    public addRentedDeliveryOrderProducer(KafkaTemplate<String, AddRentedDeliveryOrderDto2> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, AddRentedDeliveryOrderDto2 addRentedDeliveryOrderDto) {

        kafkaTemplate.send(topic, addRentedDeliveryOrderDto);
        log.info(" data from the Rental microservice:" + addRentedDeliveryOrderDto);
    }

}