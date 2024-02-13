package com.example.demo.repository;

import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

// JPA
// JpaRepository --> CRUD Method 제공
public interface BookRepository extends JpaRepository<Book, Long> { // Table 정보, PK 타입 지정
    // R(Read) : 전체 리스트 조회 -> findALl()
    // R(Read) : 특정 레코드 가져오기
    // 1. Id(PK) 값으로 레코드 가져오기 -> findById( PK값 )
    // 2. Id가 아닌 다른 레코드 가져올 경우 : 쿼리메서드(규칙) 사용 -> findBy+속성명
    // -> ex) findByTitle(String title), findByTitleANDPrice(String title, int price)
    // 3. 사용자 정의 쿼리문 메서드 생성
    // @Query("select * from book")
    // C(Create) : 데이터 입력하기 -> save()
    // U(Update) : 특정 레코드 수정하기 -> save( PK값 )
}
