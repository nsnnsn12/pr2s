package com.metacrew.pr2s.controller;

import com.metacrew.pr2s.service.memberservice.ClientMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private ClientMemberService clientMemberService;

    @GetMapping("/loginPage")
    public String loginPage(){
        return "user/body/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String loginId, @RequestParam("password") String password) {
        if(!clientMemberService.validateLoginCheck(loginId, password)) return "user/body/login";
        return "redirect:/hello";
    }
}
