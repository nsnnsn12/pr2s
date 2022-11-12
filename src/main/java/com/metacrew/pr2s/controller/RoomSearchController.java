package com.metacrew.pr2s.controller;

import com.metacrew.pr2s.dto.condition.RoomSearchConditionDto;
import com.metacrew.pr2s.dto.room.SearchedRoomDto;
import com.metacrew.pr2s.service.roomsearchservice.RoomSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/room-search")
@RequiredArgsConstructor
@Slf4j
public class RoomSearchController {
    private final RoomSearchService roomSearchService;

    @PostMapping("/user/search")
    @ResponseBody
    public String searchByReservationCondition(@RequestBody RoomSearchConditionDto roomSearchConditionDto, Pageable pageable){
        log.info("roomSearchConditionDto:{}", roomSearchConditionDto);
        Page<SearchedRoomDto> result = roomSearchService.searchByReservationCondition(roomSearchConditionDto, pageable);
        return getSearchList();
    }

    public String getSearchList(){
        return "room/body/user-condition";
    }
}
