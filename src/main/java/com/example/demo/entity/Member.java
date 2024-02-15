package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Member {

    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AI
    private Long id; // 일련번호 : 1 2 3 4 ...
    @Column(length = 50, nullable = false, unique = true) // 길이 50, null X, 중복 X
    private String username; // 사용자 ID
    private String password; // 사용자 비밀번호

    private String uname; // 이름
    private int age; // 나이
    private String uemail; // 이메일

    // 계정 권한 (admin, manager, user ...)
    // 회원 한명당 권한 '1개'? OR '여러개' -> 여러개로 선택
}
