package com.metacrew.pr2s.controller;

import com.metacrew.pr2s.service.memberservice.ClientMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private ClientMemberService clientMemberService;

    @GetMapping("/login")
    public String loginPage(){
        return "user/body/login";
    }

    @PostMapping("/login")
    public String login(Model model, @RequestParam("email") String loginId, @RequestParam("password") String password, RedirectAttributes redirectAttributes) {
        if(!clientMemberService.validLoginCheck(loginId, password)) {
            redirectAttributes.addAttribute("isValidLogin", false);
            return "redirect:/member/login";
        }
        return "redirect:/hello";
    }

    @GetMapping("/register")
    public String register(){
        return "user/body/register";
    }
}
