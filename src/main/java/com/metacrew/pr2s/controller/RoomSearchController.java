package com.metacrew.pr2s.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/room-search")
@RequiredArgsConstructor
@Slf4j
public class RoomSearchController {
    @GetMapping("/user/search")
    public String search(){
        // TODO: 2022-10-04 일반 사용자 방 조회 기능
        return "";
    }
}
