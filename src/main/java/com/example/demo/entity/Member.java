package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

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
    @ManyToMany(fetch = FetchType.EAGER) // M:N (다:다 관계 구조)
    @JoinTable(
            name = "member_roles", // 관계 테이블(JPA 로 자동 핸들링)
            joinColumns = @JoinColumn(name = "member_id"), // FK, member -> id(PK)
            inverseJoinColumns = @JoinColumn(name = "role_id") // FK, role -> id(PK)
    )
    private Set<Role> roles; // Set : 중복 X --> USER, MANAGER, ADMIN ...
}
