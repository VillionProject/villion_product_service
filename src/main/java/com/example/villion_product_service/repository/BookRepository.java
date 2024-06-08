package com.example.villion_product_service.repository;

import com.example.villion_product_service.domain.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    BookEntity findByBookId(Long bookId);
}
