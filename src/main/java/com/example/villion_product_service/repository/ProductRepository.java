package com.example.villion_product_service.repository;

import com.example.villion_product_service.domain.entity.ProductEntity;
import com.example.villion_product_service.domain.eunm.RentalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    ProductEntity findByProductId(Long productId);

    List<ProductEntity> findAllByRentalLocationAndRentalStatusNot(String rentalLocation, RentalStatus status);
    List<ProductEntity> findAllByRentalStatusNot(RentalStatus status);

    List<ProductEntity> findAllByBookNameAndProductIdNot(String bookName, Long productId);
}
