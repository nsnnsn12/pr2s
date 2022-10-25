package com.metacrew.pr2s.controller;

import com.metacrew.pr2s.dto.AddressDto;
import com.metacrew.pr2s.dto.InstitutionCreateDto;
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
    public String juso(Model model) {
        model.addAttribute("addressDto", new AddressDto());
        return "common/address/juso";
    }

    @GetMapping("/popUp")
    public String jusoPopupOpen(HttpServletRequest request, Model model) {
        String firstYn = request.getParameter("firstYn");
        if(firstYn == null) firstYn = "N";
        String confmKey = "U01TX0FVVEgyMDIyMTAxMzIxMDUxODExMzA1MzA=";

        model.addAttribute("confmKey", confmKey);
        model.addAttribute("firstYn", firstYn);

        return "common/address/popUp";
    }

    @PostMapping("/popUp")
    public String jusoPopupClose(HttpServletRequest request, Model model) {
        String inputYn = request.getParameter("inputYn");
        String firstYn = "N";

        String roadFullAddr = request.getParameter("roadFullAddr");
        String roadAddrPart1 = request.getParameter("roadAddrPart1");
        String roadAddrPart2 = request.getParameter("roadAddrPart2");
        String engAddr = request.getParameter("engAddr");
        String jibunAddr = request.getParameter("jibunAddr");
        String zipNo = request.getParameter("zipNo");
        String admCd = request.getParameter("admCd");
        String rnMgtSn = request.getParameter("rnMgtSn");
        String bdMgtSn = request.getParameter("bdMgtSn");
        String detBdNmList = request.getParameter("detBdNmList");
        String bdNm = request.getParameter("bdNm");
        String bdKdcd = request.getParameter("bdKdcd");
        String siNm = request.getParameter("siNm");
        String sggNm = request.getParameter("sggNm");
        String emdNm = request.getParameter("emdNm");
        String liNm = request.getParameter("liNm");
        String rn = request.getParameter("rn");
        String udrtYn = request.getParameter("udrtYn");
        String buldMnnm = request.getParameter("buldMnnm");
        String buldSlno = request.getParameter("buldSlno");
        String mtYn = request.getParameter("mtYn");
        String lnbrMnnm = request.getParameter("lnbrMnnm");
        String lnbrSlno = request.getParameter("lnbrSlno");
        String emdNo = request.getParameter("emdNo");
        String entX = request.getParameter("entX");
        String entY = request.getParameter("entY");

        String confmKey = "U01TX0FVVEgyMDIyMTAxMzIxMDUxODExMzA1MzA=";

        model.addAttribute("confmKey", confmKey);
        model.addAttribute("firstYn", firstYn);

        model.addAttribute("roadFullAddr", roadFullAddr);
        model.addAttribute("roadAddrPart1", roadAddrPart1);
        model.addAttribute("roadAddrPart2", roadAddrPart2);
        model.addAttribute("engAddr", engAddr);
        model.addAttribute("jibunAddr", jibunAddr);
        model.addAttribute("zipNo", zipNo);
        model.addAttribute("admCd", admCd);
        model.addAttribute("rnMgtSn", rnMgtSn);
        model.addAttribute("bdMgtSn", bdMgtSn);
        model.addAttribute("detBdNmList", detBdNmList);
        model.addAttribute("bdNm", bdNm);
        model.addAttribute("bdKdcd", bdKdcd);
        model.addAttribute("siNm", siNm);
        model.addAttribute("sggNm", sggNm);
        model.addAttribute("emdNm", emdNm);
        model.addAttribute("liNm", liNm);
        model.addAttribute("rn", rn);
        model.addAttribute("udrtYn", udrtYn);
        model.addAttribute("buldMnnm", buldMnnm);
        model.addAttribute("buldSlno", buldSlno);
        model.addAttribute("mtYn", mtYn);
        model.addAttribute("lnbrMnnm", lnbrMnnm);
        model.addAttribute("lnbrSlno", lnbrSlno);
        model.addAttribute("emdNo", emdNo);
        model.addAttribute("entX", entX);
        model.addAttribute("entY", entY);

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

    @GetMapping("/inst")
    public String inst(Model model) {
        model.addAttribute("institutionDto", new InstitutionCreateDto());
        model.addAttribute("addressDto", new AddressDto());
        return "manager/body/inst_register";
    }

}
