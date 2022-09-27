package com.metacrew.pr2s.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    //로그인 페이지
    @GetMapping("/login")
    public String loginPage(){
        return getLoginPage();
    }
    
    public String getLoginPage(){
        return "member/body/login";
    }
}
