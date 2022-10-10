package com.metacrew.pr2s.controller;

import com.metacrew.pr2s.dto.JoinMemberDto;
import com.metacrew.pr2s.dto.condition.RoomSearchConditionDto;
import com.metacrew.pr2s.validator.SearchConditionValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/room-search")
@RequiredArgsConstructor
@Slf4j
public class RoomSearchController {
    private final SearchConditionValidator searchConditionValidator;

    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(searchConditionValidator);
    }

    @GetMapping("/user/search")
    public String searchByReservationCondition(@Valid RoomSearchConditionDto roomSearchConditionDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "redirect:/room-search/user/search";
        }
        // TODO: 2022-10-04 일반 사용자 방 조회 기능
        return "";
    }
}
