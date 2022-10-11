package com.metacrew.pr2s.service.roomsearchservice;

import com.metacrew.pr2s.dto.condition.RoomSearchConditionDto;
import com.metacrew.pr2s.dto.room.SearchedRoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 방 조회를 위한 서비스 로직을 가지고 있는 클래스이다.
 * @author sunggyu
 * @since 2022.10.10
 */
@Service
@Transactional
@RequiredArgsConstructor
public class RoomSearchService {
    /**
     * 예약을 위한 방 조회 기능
     * 예약 시 필요한 방 정보만을 dto로 담아 리턴한다.
     * @author sunggyu
     * @since 2022.10.10
     * @param roomSearchConditionDto 검색조건
     */
    public List<SearchedRoomDto> searchByReservationCondition(RoomSearchConditionDto roomSearchConditionDto){
        //usage는 여기서 조회해서 가져와야 할 듯
        return new ArrayList<>();
    }
}
