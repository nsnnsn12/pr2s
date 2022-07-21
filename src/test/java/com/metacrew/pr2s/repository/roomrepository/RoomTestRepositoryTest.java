package com.metacrew.pr2s.repository.roomrepository;

import com.metacrew.pr2s.dto.RoomDto;
import com.metacrew.pr2s.entity.Room;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;


import javax.persistence.EntityManager;
import javax.transaction.Transactional;


import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class RoomTestRepositoryTest {
    @Autowired
    RoomTestRepository roomTestRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("등록 테스트")
    void register() {
        //given
        Room room = Room.createRoomByRoomDto(roomDtoTestData(), null, null);
        //roomTestRepository.register(room);
        roomRepository.save(room);
        entityManager.flush();
        entityManager.clear();
        //when
        //Room findRoom = roomTestRepository.findByRoomLongId(room.getId());
        Room findRoom = roomRepository.findByRoomId(room.getId());

        //then
        assertThat(findRoom.getTitle()).isEqualTo(room.getTitle());
    }

    public RoomDto roomDtoTestData() {
        RoomDto roomDto =new RoomDto();
        roomDto.setTitle("방이름이다");
        roomDto.setDescription("방설명이다");
        roomDto.setMaximumPersonCount(100);
        return roomDto;

    }

    @Test
    @DisplayName("제목을 이용한 조회 테스트")
    void findByRoomTitle(){
        //given
        Room room = Room.createRoomByRoomDto(roomDtoTestData(), null, null);
        roomRepository.save(room);
        entityManager.flush();
        entityManager.clear();

        //when
        List<Room> findRoom = roomTestRepository.findByRoomTitle("방이름이다");

        //then
        assertThat(findRoom).extracting("title").contains("방이름이다");
    }

}