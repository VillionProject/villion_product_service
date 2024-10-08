package com.example.villion_product_service.service;

import com.example.villion_product_service.client.UserServiceClient;
import com.example.villion_product_service.domain.dto.GetLibraryWithProductDto;
import com.example.villion_product_service.domain.entity.ProductEntity;
import com.example.villion_product_service.domain.eunm.Category;
import com.example.villion_product_service.domain.eunm.RentalStatus;
import com.example.villion_product_service.kafka.consumer.GetProductsByLocationConsumer;
import com.example.villion_product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final GetProductsByLocationConsumer getProductsByLocationConsumer;
    private final UserServiceClient userServiceClient;

    public List<ProductEntity> getProductsByLocation() {
//        getProductsByLocationConsumer.getProductsByLocation();


        // #1 user-service에서 kafka로 user의 위치 가져오기(kafka:비동기적이고 실시간으로 변경되는 이벤트 기반 데이터 처리에 적합:위치)
        // -> userID를 브라우저에서 가져올 수 없나? -> 안돼. 왜냐면 user의 위치가 필요한거라서
        String location = getProductsByLocationConsumer.getLocation();
//        String location = "인계동";

        if (location == null) {
            throw new IllegalStateException("Location not set. Ensure the location is received from Kafka first.");
        }

        // 대여 상태가 UNAVAILABLE인 것은 제외하고 제품 전체 보여주기
        List<ProductEntity> allByRentalLocation = productRepository.findAllByRentalLocationAndRentalStatusNot(location, RentalStatus.UNAVAILABLE);




        if (location.equals("지역 미지정")) { // #2 지정 미지정일 경우, 전체보기
            // 빌릴 수 있는 상품 "전체" 보여주기(Feign Client 사용)
            return productRepository.findAllByRentalStatusNot(RentalStatus.UNAVAILABLE);


        } else { // #3 위치에 해당하는 상품 보여주기
            return allByRentalLocation;
            // product-service에서 return값을 user-service로 openFeign으로 보내주면 됨..(openFeign:HTTP 기반의 다른 마이크로서비스나 외부 API와의 통합에 적합)
        }
    }


    public ProductEntity getProductDetail(Long productId) {

        return productRepository.findByProductId(productId);
    }

    public List<GetLibraryWithProductDto> getLibraryWithProduct(Long productId) {
        ProductEntity byProductId = productRepository.findByProductId(productId);


        List<ProductEntity> allByBookNameAndProductIdNot = productRepository.findAllByBookNameAndProductIdNot(byProductId.getBookName(), productId);

        List<GetLibraryWithProductDto> libraryWithProductDtos = new ArrayList<>();
        for(ProductEntity product : allByBookNameAndProductIdNot) {
            Long ownerUserId = product.getOwnerUserId();
            String libraryWithProduct = userServiceClient.getLibraryWithProduct(ownerUserId);

            ModelMapper mapper = new ModelMapper();
            GetLibraryWithProductDto map = mapper.map(product, GetLibraryWithProductDto.class);
            map.setLibraryName(libraryWithProduct);

            libraryWithProductDtos.add(map);
        }
        return libraryWithProductDtos;
    }

    public List<ProductEntity> getProductsByCategory(Long productId) {
        ProductEntity byProductId = productRepository.findByProductId(productId);
     return productRepository.findAllByCategory(byProductId.getCategory());
    }

    public List<ProductEntity> searchProduct(String word) {
        return productRepository.searchByFullText(word);
    }

    public List<ProductEntity> suggestedCategory(Category category) {
       return productRepository.findAllByCategory(category);
    }

    public List<ProductEntity> getCategory(Category category) {
        return productRepository.findAllByCategory(category);
    }

    public ProductEntity getProduct(Long productId) {
        return productRepository.findByProductId(productId);
    }
}
