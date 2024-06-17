package com.example.villion_product_service.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductCountRepository {
    // redis 명령어 실행을 위한 redisTemplate 변수 추가
    private final RedisTemplate<String, String> redisTemplate;

// TODO redis 값 어떻게 들어오는지 확인하는거 하면 됨..

    // redis의 incr명령어를 사용학 위한 increment method 작성
    // productId별 등록된 orderedQuantity 개수 증가
    public Long increment(String productId, long orderedQuantity) {
        return redisTemplate
                .opsForValue()
                .increment("order_count:" + productId, orderedQuantity);
    }


    // 특정 productId의 order_count를 rentalQuantity로 설정하는 메소드
    public void setOrderCount(String productId, Long rentalQuantity) {
        redisTemplate
                .opsForValue()
                .set("order_count:" + productId, rentalQuantity.toString());
    }

    // 특정 productId별 값을 가져오는 메소드
    public String getOrderCount(String productId) {
        return redisTemplate
                .opsForValue()
                .get("order_count:" + productId);
    }

    // 특정 productId별 값을 0으로 초기화하는 메소드
    public void resetOrderCount(String productId) {
        setOrderCount(productId, 0L);
    }

    // 특정 productId별 키 삭제
    public void deleteOrderCount(String productId) {
        redisTemplate.delete("order_count:" + productId);
    }
}
