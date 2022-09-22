package com.metacrew.pr2s.controller;

import com.metacrew.pr2s.dto.AddressDto;
import com.metacrew.pr2s.dto.MailDto;
import com.metacrew.pr2s.service.addressservice.InstitutionAddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {
    @Autowired
    private InstitutionAddressService institutionAddressService;

    @GetMapping("/juso")
    public String sample(Model model) {
        model.addAttribute("addressDto", new AddressDto());
        return "common/address/juso";
    }

    @RequestMapping("/popUp")
    public String pop(HttpServletRequest request, Model model){

        String confmKey = "devU01TX0FVVEgyMDIyMDkwNDE5MTUxOTExMjk0NjU="; // 검색API 승인키
        String domain = "http://www.juso.go.kr"; // 인터넷망
        // ※ 행정망 내에서 운영되는 시스템도 이용 가능합니다. 행정망 서비스를 위한 API 요청URL은 별도로 문의 주시기 바랍니다.(1588-0061)
        String resultType = "4"; // 검색결과 화면 출력유(1 : 도로명, 2 : 도로명+지번, 3 : 도로명+상세건물명, 4 : 도로명+지번+상세건물명)

        model.addAttribute("confmKey",confmKey);
        model.addAttribute("domain",domain);
        model.addAttribute("resultType",resultType);
        return "common/address/popUp";
    }

    @PostMapping("/saveAddress")
    public String saveAddress(@Valid AddressDto addressDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "redirect:/address/juso";
        }
        institutionAddressService.saveAddress(addressDto);
        return "redirect:/";

    }



}
