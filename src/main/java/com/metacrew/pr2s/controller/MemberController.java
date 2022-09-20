package com.metacrew.pr2s.controller;

import com.metacrew.pr2s.dto.JoinMemberDto;
import com.metacrew.pr2s.dto.MailDto;
import com.metacrew.pr2s.service.memberservice.ClientMemberService;
import com.metacrew.pr2s.service.senderservice.EmailSenderService;
import com.metacrew.pr2s.validator.JoinMemberValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Cache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final ClientMemberService clientMemberService;
    private final JoinMemberValidator joinMemberValidator;

    private final EmailSenderService emailSenderService;

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

        String uuid = setCacheJoinMember(joinMemberDto);
        emailSenderService.sendSimpleMessage(new MailDto(joinMemberDto.getEmail(), "PR2S 가입 인증 메일입니다.", String.format("http://localhost:8080/auth/confirm/%s", uuid)));
        return "redirect:/auth/login";
    }

    private String setCacheJoinMember(JoinMemberDto joinMemberDto){
        String uuid = UUID.randomUUID().toString();
        log.info("uuid 확인 : {} ", String.format("http://localhost:8080/auth/confirm/%s", uuid));
        clientMemberService.putCacheJoinMember(joinMemberDto, uuid);

        return uuid;
    }

    @GetMapping("/confirm/{uuid}")
    public String signupConfirmByEmail(@PathVariable("uuid") String uuid){
        JoinMemberDto joinMemberDto = clientMemberService.getCacheJoinMember(uuid);
        if(joinMemberDto == null) {
            return "redirect:/auth/alert/false";
        }
        log.info("uuid를 이용한 캐싱 정보 확인 : {}", joinMemberDto.toString());
        clientMemberService.join(joinMemberDto);
        //캐시 정보 삭제
        clientMemberService.removeCacheJoinMember(uuid);
        return "redirect:/auth/alert/true";
    }

    @GetMapping("/alert/{agree}")
    public String alert(Model model, @PathVariable("agree") boolean agree){
        log.info("확인"+agree);
        model.addAttribute("agree", agree);
        return "auth/body/alert";
    }
}
