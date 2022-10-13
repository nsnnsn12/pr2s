package com.metacrew.pr2s.service.roomsearchservice;

import com.metacrew.pr2s.dto.condition.RoomSearchConditionDto;
import com.metacrew.pr2s.dto.room.SearchedRoomDto;
import com.metacrew.pr2s.entity.Room;
import com.metacrew.pr2s.repository.roomrepository.RoomQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    private final RoomQueryRepository roomQueryRepository;
    /**
     * 예약을 위한 방 조회 기능
     * 예약 시 필요한 방 정보만을 dto로 담아 리턴한다.
     * @author sunggyu
     * @since 2022.10.10
     * @param roomSearchConditionDto 검색조건
     */
    public Page<SearchedRoomDto> searchByReservationCondition(RoomSearchConditionDto roomSearchConditionDto, Pageable pageable){
        Page<Room> results = roomQueryRepository.search(roomSearchConditionDto, pageable);
        List<SearchedRoomDto> list = new ArrayList<>();
        return new PageImpl<>(list, pageable, list.size());
    }
}
