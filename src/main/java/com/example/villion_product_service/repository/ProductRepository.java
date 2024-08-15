package com.example.villion_product_service.repository;

import com.example.villion_product_service.domain.entity.ProductEntity;
import com.example.villion_product_service.domain.eunm.Category;
import com.example.villion_product_service.domain.eunm.RentalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    ProductEntity findByProductId(Long productId);

    List<ProductEntity> findAllByRentalLocationAndRentalStatusNot(String rentalLocation, RentalStatus status);
    List<ProductEntity> findAllByRentalStatusNot(RentalStatus status);

    List<ProductEntity> findAllByBookNameAndProductIdNot(String bookName, Long productId);

    List<ProductEntity> findAllByCategory(Category category);

    @Query(value = "SELECT * FROM PRODUCTS WHERE MATCH(book_name, description) AGAINST(:word IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    List<ProductEntity> searchByFullText(@Param("word") String word);
}
