package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 트랜잭션 처리
public class BookService {

    @Autowired
    private BookRepository repository;

    // 전체 리스트 조회
    public List<Book> getList() {
        return repository.findAll();
    }
}
