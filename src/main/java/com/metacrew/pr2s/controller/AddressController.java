package com.metacrew.pr2s.controller;

import com.metacrew.pr2s.dto.AddressDto;
import com.metacrew.pr2s.entity.Address;
import com.metacrew.pr2s.repository.AddressRepository;
import com.metacrew.pr2s.service.addressservice.AddressService;
import com.metacrew.pr2s.service.addressservice.InstitutionAddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Locale;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AddressController {
    @Autowired
    private InstitutionAddressService institutionAddressService;

    @GetMapping("/juso")
    public String sample() {
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
    public String saveAddress(AddressDto addressDto){

        institutionAddressService.saveAddress(addressDto);

        return "redirect:/juso";
    }



}
