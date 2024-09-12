package com.example.villion_product_service.kafka.consumer;

import com.example.villion_product_service.domain.entity.ProductEntity;
import com.example.villion_product_service.domain.eunm.RentalStatus;
import com.example.villion_product_service.kafka.config.TopicConfig;
import com.example.villion_product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateRentalStatusConsumer {

    private final ProductRepository productRepository;

    @KafkaListener(topics = TopicConfig.updateRentalStatus)
    public void updateRentalStatus(String message) {
        // 메시지를 파싱하고 상태를 업데이트합니다.
        String[] parts = message.split(",");
        Long productId = Long.parseLong(parts[0].split(":")[1]);
        String status = parts[1].split(":")[1];
        Long quantity = Long.parseLong(parts[2].split(":")[1]);

        ProductEntity product = productRepository.findById(productId).orElseThrow();
        product.setRentalStatus(RentalStatus.valueOf(status));
        product.setStockQuantity(product.getStockQuantity() + quantity);
        productRepository.save(product);

        System.out.println("ProdcutId : " + product.getProductId() + ", StockQuantity : " + product.getStockQuantity() + ", quantity :" + quantity);
        System.out.println("변경완---------------------------------------");
    }
}
