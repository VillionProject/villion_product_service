package com.example.villion_product_service.kafka.producer;

import com.example.villion_product_service.domain.dto.AddRentedDeliveryOrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AddRentedDeliveryOrderProducer {

    private final KafkaTemplate<String, AddRentedDeliveryOrderDto> kafkaTemplate;

    public AddRentedDeliveryOrderProducer(KafkaTemplate<String, AddRentedDeliveryOrderDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, AddRentedDeliveryOrderDto addRentedDeliveryOrderDto) {

        kafkaTemplate.send(topic, addRentedDeliveryOrderDto);
        log.info(" data from the Rental microservice:" + addRentedDeliveryOrderDto);
    }

}
