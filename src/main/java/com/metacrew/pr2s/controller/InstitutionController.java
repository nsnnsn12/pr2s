package com.metacrew.pr2s.controller;

import com.metacrew.pr2s.dto.AddressDto;
import com.metacrew.pr2s.dto.InstitutionCreateDto;
import com.metacrew.pr2s.service.addressservice.InstitutionAddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/inst")
@RequiredArgsConstructor
public class InstitutionController {


    @GetMapping("/register")
    public String inst(Model model) {
        model.addAttribute("institutionDto", new InstitutionCreateDto());
        model.addAttribute("addressDto", new AddressDto());
        return "manager/body/inst_register";
    }
}


