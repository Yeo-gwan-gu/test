package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Book { // Book --> Table

    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AutoIncrement(AI)
    private Long id; // 일련번호 : 1 2 3 4
    @Column(length = 50, nullable = false) // 길이 지정, null 허용 x
    private String title; // 제목
    private int price; // 가격
    private String name; // 저자명
    private int page; // 페이지수

}
