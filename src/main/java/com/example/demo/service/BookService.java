package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // 트랜잭션 처리
public class BookService {

    @Autowired
    private BookRepository repository;

    // 전체 리스트 조회
    public List<Book> getList() {
        return repository.findAll();
    }

    // 특정 레코드 가져오기
    public Book getById(Long id) {
        Optional<Book> optional = repository.findById(id);
        // id 값 존재 여부 검증
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Book not found with id: " + id);
        }
    }

    // 데이터 입력하기
    public Book register(Book book) {
        return repository.save(book);
    }

}
