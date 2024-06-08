package com.example.villion_product_service.service;

import com.example.villion_product_service.domain.entity.BookEntity;
import com.example.villion_product_service.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

//    public List<BookEntity> getBook(Long bookId) {
//        ModelMapper mapper = new ModelMapper();
//        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//        BookEntity byBookId = bookRepository.findByBookId(bookId);
//        BookEntity bookEntity = mapper.map(byBookId, BookEntity.class);
//
//        return bookEntity;
//    }
}
