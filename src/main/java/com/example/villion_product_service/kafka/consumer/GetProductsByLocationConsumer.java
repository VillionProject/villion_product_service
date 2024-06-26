package com.example.villion_product_service.kafka.consumer;

import com.example.villion_product_service.domain.entity.ProductEntity;
import com.example.villion_product_service.kafka.config.TopicConfig;
import com.example.villion_product_service.repository.ProductRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetProductsByLocationConsumer {
    private String location;

    @KafkaListener(topics = TopicConfig.getProductsByLocation)
    public void getProductsByLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}
