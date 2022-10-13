package com.metacrew.pr2s.repository.roomrepository;

import com.metacrew.pr2s.dto.condition.RoomSearchConditionDto;
import com.metacrew.pr2s.entity.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@Rollback
class RoomQueryRepositoryTest {
    @Autowired
    private RoomQueryRepository roomQueryRepository;

    @Test
    @DisplayName("방 조회 테스트")
    void saveRoomTest(){
        // TODO: 2022-10-13 방 등록 기능이 완료되면 테스트 작성 예정
        //given
        RoomSearchConditionDto roomSearchConditionDto = new RoomSearchConditionDto();
        Pageable pageable = Pageable.ofSize(3);

        //when
        Page<Room> results = roomQueryRepository.search(roomSearchConditionDto, pageable);

        //then
        //assertThat(findRoom.isPresent()).isEqualTo(true);
    }
}