//package com.example.villion_product_service.kafka;
//
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class ConsumerConfig {
//
//    // 받을때 JSON 받겠다.
//    @Bean
//    public RecordMessageConverter converter() {
//        return new JsonMessageConverter();
//    }
//
//    // 만약 data가 넘어오는 도중에 에러가 낫다면 1초에 2번씩 더 요청할 것이다.
//    @Bean
//    public CommonErrorHandler errorHandler(KafkaOperations<Object, Object> kafkaOperations) {
//        return new DefaultErrorHandler(
//                new DeadLetterPublishingRecoverer(kafkaOperations),
//                new FixedBackOff(1000L, 2)
//        );
//    }
//}
