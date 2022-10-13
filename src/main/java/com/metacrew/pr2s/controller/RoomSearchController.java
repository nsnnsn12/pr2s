package com.metacrew.pr2s.controller;

import com.metacrew.pr2s.dto.JoinMemberDto;
import com.metacrew.pr2s.dto.condition.RoomSearchConditionDto;
import com.metacrew.pr2s.dto.room.SearchedRoomDto;
import com.metacrew.pr2s.service.roomsearchservice.RoomSearchService;
import com.metacrew.pr2s.validator.SearchConditionValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/room-search")
@RequiredArgsConstructor
@Slf4j
public class RoomSearchController {
    private final SearchConditionValidator searchConditionValidator;
    private final RoomSearchService roomSearchService;

    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(searchConditionValidator);
    }

    @GetMapping("/user/search")
    public String searchByReservationCondition(@Valid RoomSearchConditionDto roomSearchConditionDto, BindingResult bindingResult, Model model, Pageable pageable){
        if(bindingResult.hasErrors()){
            return "redirect:/room-search/user/search";
        }
        Page<SearchedRoomDto> result = roomSearchService.searchByReservationCondition(roomSearchConditionDto, pageable);
        model.addAttribute("roomList", result);
        return getSearchList();
    }

    public String getSearchList(){
        return "room/body/user-condition";
    }
}
