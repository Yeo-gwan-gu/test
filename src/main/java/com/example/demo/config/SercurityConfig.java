package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity // 웹 보안을 활성화
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SercurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // 회원가입 시 비밀번호 암호화에 사용할 Encoder 빈 등록
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
//                        .requestMatchers("/ui/**").permitAll() // "/ui/**" 경로는 인증없이 접근 허용
//                        .requestMatchers("/admin/**").hasRole("ADMIN") // "/admin/" 로 시작하는 경로는 ADMIN 권한만 접근 가능
//                        .requestMatchers("/user/**").hasAnyRole("ADMIN", "USER") // "/user/" 로 시작하는 경로는 ADMIN 또는 USER 권한만 접근 가능
//                        .anyRequest().authenticated() // 나머지 모든 요청은 인증 요구
                                .requestMatchers("/api/**").authenticated() // "/api/**" 경로는 인증 후 접근
                                .requestMatchers("/book/**").authenticated() // "/book/**" 경로는 인증 후 접근
                                .anyRequest().permitAll() // 나머지 요청은 인증 없이 접근 허용
                )
                .formLogin(form -> form
                        .loginPage("/ui/list") // 사용자 정의 로그인 페이지
                        .loginProcessingUrl("/login") // 로그인 처리 URL
                        .defaultSuccessUrl("/ui/list", true) // 로그인 성공 후 리다이렉션 할 기본 URL
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // 로그아웃 URL
                        .logoutSuccessUrl("/ui/list") // 로그아웃 성공 후 리다이렉션 할 URL
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) // GET 요청으로 로그아웃 허용
                        .clearAuthentication(true) // 로그아웃 시 인증번호 클리어(SecurityContext)
                        .deleteCookies("JSESSIONID") // 로그아웃 시 삭제할 쿠키 이름
                        .invalidateHttpSession(true) // 세션 무효화
                ).oauth2Login(oauth2Login -> oauth2Login // 소셜 로그인
                        .loginPage("/ui/list")
                        .defaultSuccessUrl("/ui/list")
                );
        return http.build();
    }
}
