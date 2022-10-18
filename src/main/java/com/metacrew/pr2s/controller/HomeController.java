package com.metacrew.pr2s.controller;

import com.metacrew.pr2s.dto.SidoSigunguDto;
import com.metacrew.pr2s.entity.enums.SidoSigungu;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {
    @GetMapping({"/", "index"})
    public String index(Model model){
        List<SidoSigunguDto> sidoSigunguDtoList = new ArrayList<>();
        SidoSigungu[] values = SidoSigungu.values();
        for(SidoSigungu value : values){
            sidoSigunguDtoList.add(new SidoSigunguDto(value));
        }

        model.addAttribute("locations", sidoSigunguDtoList);
        return "common/body/index";
    }
}
