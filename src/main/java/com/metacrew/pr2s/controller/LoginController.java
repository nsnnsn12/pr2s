package com.metacrew.pr2s.controller;

import com.metacrew.pr2s.dto.JoinMemberDto;
import com.metacrew.pr2s.dto.MailDto;
import com.metacrew.pr2s.service.memberservice.ClientMemberService;
import com.metacrew.pr2s.service.senderservice.EmailSenderService;
import com.metacrew.pr2s.validator.JoinMemberValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final ClientMemberService clientMemberService;
    private final JoinMemberValidator joinMemberValidator;

    /* 커스텀 유효성 검증을 위해 추가 */
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(joinMemberValidator);
    }

    //로그인 페이지
    @GetMapping("/login")
    public String loginPage(){
        return getLoginPage();
    }
    
    public String getLoginPage(){
        return "auth/body/login";
    }
}
