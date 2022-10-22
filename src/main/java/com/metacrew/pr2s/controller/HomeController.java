package com.metacrew.pr2s.controller;

import com.metacrew.pr2s.dto.RoomUsageDto;
import com.metacrew.pr2s.dto.SidoSigunguDto;
import com.metacrew.pr2s.entity.enums.SidoSigungu;
import com.metacrew.pr2s.entity.enums.Usage;
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
        List<RoomUsageDto> usageDtoList = new ArrayList<>();
        for(SidoSigungu sidoSiGungu : SidoSigungu.values()){
            sidoSigunguDtoList.add(new SidoSigunguDto(sidoSiGungu));
        }

        for(Usage usage : Usage.values()){
            usageDtoList.add(new RoomUsageDto(usage));
        }

        model.addAttribute("locations", sidoSigunguDtoList);
        model.addAttribute("usages", usageDtoList);
        return "common/body/index";
    }
}
