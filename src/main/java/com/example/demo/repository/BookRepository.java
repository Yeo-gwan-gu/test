package com.example.demo.repository;

import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

// JPA
// JpaRepository --> CRUD Method 제공
public interface BookRepository extends JpaRepository<Book, Long> { // Table 정보, PK 타입 지정
    // R(Read) : 전체 리스트 조회 -> findALl()
    // C(Create) : 데이터 입력하기 -> save()
}
