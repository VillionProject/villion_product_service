package com.example.villion_product_service.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductCountRepository {
    // redis 명령어 실행을 위한 redisTemplate 변수 추가
    private final RedisTemplate<String, String> redisTemplate;


    // redis의 incr명령어를 사용학 위한 increment method 작성
    // productId별 등록된 rentalQuantity 개수까지만 증가
    public Long increment(String productId) {
        return redisTemplate
                .opsForValue()
                .increment("order_count:" + productId);
    }


    // 특정 productId의 order_count를 rentalQuantity로 설정하는 메소드
    public void setOrderCount(String productId, Long rentalQuantity) {
        redisTemplate
                .opsForValue()
                .set("order_count:" + productId, rentalQuantity.toString());
    }
}
