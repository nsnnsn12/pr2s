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

    //로그인 페이지
    @GetMapping("/login")
    public String loginPage(){
        return getLoginPage();
    }

    //로그인 페이지
    @PostMapping("/login")
    public String login(Model model, @RequestParam("email") String loginId, @RequestParam("password") String password, RedirectAttributes redirectAttributes) {
        if(!clientMemberService.validLoginCheck(loginId, password)) {
            redirectAttributes.addAttribute("isValidLogin", false);
            return getLoginPage();
        }
        return "redirect:/hello";
    }
    //회원가입 페이지
    @GetMapping("/register")
    public String registerPage(JoinMemberDto joinMemberDto){
        return getRegisterPage();
    }

    //회원가입 페이지
    @PostMapping("/register")
    public String register(@Valid JoinMemberDto joinMemberDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return getRegisterPage();
        }

        String uuid = setCacheJoinMember(joinMemberDto);
        emailSenderService.sendSimpleMessage(new MailDto(joinMemberDto.getEmail(), "PR2S 가입 인증 메일입니다.", String.format("http://localhost:8080/auth/confirm/%s", uuid)));
        return "redirect:/auth/login";
    }

    //회원정보 캐시에 저장
    private String setCacheJoinMember(JoinMemberDto joinMemberDto){
        String uuid = UUID.randomUUID().toString();
        log.info("uuid 확인 : {} ", String.format("http://localhost:8080/auth/confirm/%s", uuid));
        clientMemberService.putCacheJoinMember(joinMemberDto, uuid);

        return uuid;
    }

    //이메일 인증 및 회원정보 DB에 저장
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

    //이메일 인증 여부 alert
    @GetMapping("/alert/{agree}")
    public String alert(Model model, @PathVariable("agree") boolean agree){
        log.info("확인"+agree);
        model.addAttribute("agree", agree);
        return getAlertPage();
    }

    public String getLoginPage(){
        return "auth/body/login";
    }

    public String getRegisterPage(){
        return "auth/body/register";
    }

    public String getAlertPage(){
        return "auth/body/register";
    }
}
