package com.metacrew.pr2s.controller;

import com.metacrew.pr2s.dto.JoinMemberDto;
import com.metacrew.pr2s.service.memberservice.ClientMemberService;
import com.metacrew.pr2s.validator.JoinMemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class MemberController {
    private final ClientMemberService clientMemberService;
    private final JoinMemberValidator joinMemberValidator;

    /* 커스텀 유효성 검증을 위해 추가 */
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(joinMemberValidator);
    }

    @GetMapping("/login")
    public String loginPage(){
        return "auth/body/login";
    }

    @PostMapping("/login")
    public String login(Model model, @RequestParam("email") String loginId, @RequestParam("password") String password, RedirectAttributes redirectAttributes) {
        if(!clientMemberService.validLoginCheck(loginId, password)) {
            redirectAttributes.addAttribute("isValidLogin", false);
            return "redirect:/auth/login";
        }
        return "redirect:/hello";
    }

    @GetMapping("/register")
    public String registerPage(JoinMemberDto joinMemberDto){
        return "auth/body/register";
    }

    @PostMapping("/register")
    public String register(@Valid JoinMemberDto joinMemberDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "auth/body/register";
        }
        clientMemberService.join(joinMemberDto, null);
        return "redirect:/auth/login";
    }
}
