package com.metacrew.pr2s.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/room")
public class RoomController {
    //로그인 페이지
    @GetMapping("/register")
    public String registerPage(){
        return getRegisterPage();
    }

    public String getRegisterPage(){
        return "manager/body/room_register";
    }
}
