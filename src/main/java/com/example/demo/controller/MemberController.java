package com.example.demo.controller;

import com.example.demo.entity.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @GetMapping("/memberRegister")
    public String memberRegister(){
        return "member/register"; //register.html(회원가입 UI)
    }
    @PostMapping("/memberRegister")
    public String memberRegister(Member vo){ // 파라메터수집
        memberService.memberRegister(vo);//저장완료
        // 리스트페이지로 이동
        return "redirect:/ui/list";
    }
}
