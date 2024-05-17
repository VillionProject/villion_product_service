package com.example.villion_product_service.domain.entity;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    private String category;
    private String bookName;
    private String itemId;
    private String isbn13;
    private String additionalCode;
    private String author;
    private String publisher;
    private Date publicationDate;
    private String originalPrice;
    private String sellingPrice;
    private String mainCategory;
    private String englishMainCategory;
}
