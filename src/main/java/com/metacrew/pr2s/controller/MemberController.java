package com.metacrew.pr2s.controller;

import com.metacrew.pr2s.dto.AddressDto;
import com.metacrew.pr2s.dto.JoinMemberDto;
import com.metacrew.pr2s.service.memberservice.ClientMemberService;
import com.metacrew.pr2s.validator.CheckEmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/auth")
public class MemberController {
    @Autowired
    private ClientMemberService clientMemberService;
    @Autowired
    private  CheckEmailValidator checkEmailValidator;

    /* 커스텀 유효성 검증을 위해 추가 */
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        //binder.addValidators(checkNicknameValidator);
        binder.addValidators(checkEmailValidator);
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
    public String registerPage(){
        return "auth/body/register";
    }

    @PostMapping("/register")
    public String register(Model model, @Valid JoinMemberDto joinMemberDto, Errors errors, Long fileId){
        if(errors.hasErrors()){
            model.addAttribute("joinMemberDto",joinMemberDto);

            // 유효성 통과 못한 필드와 메시지를 핸들링
            Map<String, String> validatorResult = clientMemberService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }

            return "auth/body/register";
        }
        clientMemberService.join(joinMemberDto, fileId);
        return "redirect:/auth/login";
    }
}
