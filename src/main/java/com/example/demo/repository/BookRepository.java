package com.example.demo.repository;

import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// JPA
// JpaRepository --> CRUD Method 제공
public interface BookRepository extends JpaRepository<Book, Long> { // Table 정보, PK 타입 지정
    // R(Read) : 전체 리스트 조회 -> findALl()
    // R(Read) : 특정 레코드 가져오기
    // 1. Id(PK) 값으로 레코드 가져오기 -> findById( PK값 )
    // 2. Id가 아닌 다른 레코드 가져올 경우 : 쿼리메서드(규칙) 사용 -> findBy+속성명

    // 3-1. 사용자 정의 쿼리문 메서드 생성
    List<Book> findByTitleAndName(String title, String name); // 쿼리메서드(규칙) 사용하여 생성

    // 3-2. JPQL 쿼리
    @Query("SELECT b FROM Book b")
    List<Book> bookList();

    @Query("SELECT b FROM Book b WHERE b.title=:title AND b.name=:name") // Entity 기준
    List<Book> findTitleName(@Param("title")String title, @Param("name")String name); // 규칙 미사용 -> 쿼리 어노테이션 사용

    @Query("SELECT b FROM Book b WHERE b.price > :price") // 입력한 가격보다 높은 가격의 데이터만 가져오기
    List<Book> findByPriceGreaterThan(@Param("price") int price); //@Param 생략 가능(이름이 같은 경우에만)

    // 3-4. 기본 SQL 쿼리 : nativeQuery = true -> 기본 Query 사용하겠다는 뜻
    @Query(value = "SELECT * FROM book WHERE title = ?1 AND name = ?2", nativeQuery = true)
    Book findTitleAndName(String title, String name);

    // 3-5. Querydsl : 복잡함

    // C(Create) : 데이터 입력하기 -> save()
    // U(Update) : 특정 레코드 수정하기 -> save( PK값 )
    // D(Delete) : 특정 레코드 삭제하기 -> deleteById( PK값 )
}
