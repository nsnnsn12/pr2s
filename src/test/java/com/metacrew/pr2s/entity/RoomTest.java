package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.dto.RoomDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;

class RoomTest {

    @Test
    @DisplayName("Entity Test")
    public void createRoomTest(){
        //given
        RoomDto roomDto = new RoomDto();
        roomDto.setTitle("방타이틀");
        roomDto.setDescription("방설명");
        roomDto.setMaximumPersonCount(100);

        //when
        Room room = Room.createRoomByRoomDto(roomDto,null, null);

        //then
        assertThat(room.getTitle()).isEqualTo("방타이틀");
        assertThat(room.getDescription()).isEqualTo("방설명");
        assertThat(room.getMaximumPersonCount()).isEqualTo(100);
        assertThat(room.getAddress()).isNull();
        assertThat(room.getFile()).isNull();

    }



}