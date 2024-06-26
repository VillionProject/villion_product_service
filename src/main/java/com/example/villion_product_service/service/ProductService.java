package com.example.villion_product_service.service;

import com.example.villion_product_service.domain.entity.BookEntity;
import com.example.villion_product_service.domain.entity.ProductEntity;
import com.example.villion_product_service.domain.response.ResponseAddProduct;
import com.example.villion_product_service.domain.request.RequestAddProduct;
import com.example.villion_product_service.kafka.consumer.GetProductsByLocationConsumer;
import com.example.villion_product_service.repository.BookRepository;
import com.example.villion_product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final GetProductsByLocationConsumer getProductsByLocationConsumer;

//    public void addProduct(Long userId, RequestAddProduct requestAddProduct) {
//        ModelMapper mapper = new ModelMapper();
//        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//        ProductEntity productEntity = mapper.map(requestAddProduct, ProductEntity.class);
//        productRepository.save(productEntity);
//    }

//    public List<ProductEntity> getProductsByLocation(Long userId) {
//        // TODO #1 user-service에서 kafka로 user의 위치를 가져와야함..(비동기적이고 실시간으로 변경되는 이벤트 기반 데이터 처리에 적합:위치)
//        // -> userID를 브라우저에서 가져올 수 없나? -> 안돼. 왜냐면 user의 위치가 필요한거라서
//
////
////        if(byUserId.getBase_location_id().equals("지역 미지정")) { // TODO #2 지정 미지정일 경우, 전체보기
////            // 빌릴 수 있는 상품 전체 보여주기(Feign Client 사용)
////
////
////        } else { TODO // #3 위치에 해당하는 상품 보여주기
//
//
//        // TODO product-service에서 return값을 user-service로 openFeign으로 보내주면 됨..(HTTP 기반의 다른 마이크로서비스나 외부 API와의 통합에 적합)
//        return null;
//    }

    public List<ProductEntity> getProductsByLocation() {
//        getProductsByLocationConsumer.getProductsByLocation();


        String location = getProductsByLocationConsumer.getLocation();
//        String location = "인계동";

        if (location == null) {
            throw new IllegalStateException("Location not set. Ensure the location is received from Kafka first.");
        }

        List<ProductEntity> allByRentalLocation = productRepository.findAllByRentalLocation(location);


        // #1 user-service에서 kafka로 user의 위치를 가져와야함..(kafka:비동기적이고 실시간으로 변경되는 이벤트 기반 데이터 처리에 적합:위치)
        // -> userID를 브라우저에서 가져올 수 없나? -> 안돼. 왜냐면 user의 위치가 필요한거라서

        if (location.equals("지역 미지정")) { // #2 지정 미지정일 경우, 전체보기
            // 빌릴 수 있는 상품 "전체" 보여주기(Feign Client 사용)
            return productRepository.findAll();


        } else { // #3 위치에 해당하는 상품 보여주기
            return allByRentalLocation;
            // product-service에서 return값을 user-service로 openFeign으로 보내주면 됨..(openFeign:HTTP 기반의 다른 마이크로서비스나 외부 API와의 통합에 적합)
        }
    }


}
