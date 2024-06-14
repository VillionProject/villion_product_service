package com.example.villion_product_service.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

@Component
public class TopicConfig {
    //    public final static String customerTopic = "customer";
    public final static String addProduct = "addProduct-topic";
    public final static String addDeliveryOrder = "addDeliveryOrder-topic";
    public final static String addRentedDeliveryOrder = "addRentedDeliveryOrder-topic";
    public final static String addRentedDeliveryOrderLast1 = "addRentedDeliveryOrder-topic-last1";
    public final static String addRentedDeliveryOrderLast2 = "addRentedDeliveryOrder-topic-last2";
    public final static String testTopic = "testTopic";

    @Bean
    public NewTopic addProductTopic(){
        return TopicBuilder
                .name(addProduct)
                .replicas(1)
                .partitions(1)
                .build();
    }

    @Bean
    public NewTopic addDeliveryOrderTopic(){
        return TopicBuilder
                .name(addDeliveryOrder)
                .replicas(1)
                .partitions(1)
                .build();
    }

    @Bean
    public NewTopic addRentedDeliveryOrderTopic(){
        return TopicBuilder
                .name(addRentedDeliveryOrder)
                .replicas(1)
                .partitions(1)
                .build();
    }

    @Bean
    public NewTopic addRentedDeliveryOrderLast1(){
        return TopicBuilder
                .name(addRentedDeliveryOrderLast1)
                .replicas(1)
                .partitions(1)
                .build();
    }

    @Bean
    public NewTopic addRentedDeliveryOrderLast2(){
        return TopicBuilder
                .name(addRentedDeliveryOrderLast2)
                .replicas(1)
                .partitions(1)
                .build();
    }

    @Bean
    public NewTopic testTopic(){
        return TopicBuilder
                .name(testTopic)
                .replicas(1)
                .partitions(1)
                .build();
    }
}