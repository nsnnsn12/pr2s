package com.metacrew.pr2s.service.roomservice;

import com.metacrew.pr2s.dto.RoomDto;
import com.metacrew.pr2s.entity.Address;
import com.metacrew.pr2s.entity.File;
import com.metacrew.pr2s.entity.Room;
import com.metacrew.pr2s.repository.roomrepository.RoomTestRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;


import java.util.List;

import static org.assertj.core.api.Assertions.*;
@SpringBootTest
@Transactional
@Rollback(value = false)
class RoomTestServiceTest {

    @Autowired
    RoomTestRepository roomTestRepository;
    @Autowired
    private RoomTestService roomTestService;

    @Autowired
    private EntityManager em;

    @BeforeEach
    public void init(){
        RoomDto roomDto = new RoomDto();
        roomDto.setTitle("초기화시 생성된 방");
        roomDto.setDescription("초기화 방이다");
        roomDto.setMaximumPersonCount(50);

        Room room = roomTestService.registerRoom(roomDto,null,null);

    }



    @Test
    @DisplayName("중복체크 없이 방 등록")
    void registerRoom() {
        //given
        RoomDto roomDto = roomDtoTestData();
        Room room = roomTestService.registerRoom(roomDto, null, null);
        em.flush();
        em.clear();

        //when
        Room findRoom = roomTestRepository.findByRoomLongId(room.getId());

        //then
        assertThat(findRoom.getTitle()).isEqualTo(room.getTitle());

    }

    //dto 설정
    public RoomDto roomDtoTestData() {
        RoomDto roomDto =new RoomDto();
        roomDto.setTitle("방이름");
        roomDto.setDescription("방설명");
        roomDto.setMaximumPersonCount(100);
        return roomDto;
    }

    @Test
    @DisplayName("방 수정")
    void updateRoom(){
        //given
        List<Room> list = roomTestRepository.findAllRoom();
        //Room findRoom = roomTestRepository.findByRoomLongId(1L);
        Room findRoom = list.get(0);

        //when
        RoomDto roomDto = new RoomDto();
        roomDto.setTitle("제목수정");
        roomDto.setDescription("설명도수정");

        roomTestService.updateRoom(roomDto, findRoom.getId());
        em.flush();
        em.clear();

        //then
        findRoom = roomTestRepository.findByRoomLongId(findRoom.getId());

        assertThat(findRoom.getTitle()).isEqualTo(roomDto.getTitle());
        assertThat(findRoom.getDescription()).isEqualTo(roomDto.getDescription());
        assertThat(findRoom.getMaximumPersonCount()).isEqualTo(roomDto.getMaximumPersonCount());
    }

//    @Test
//    @DisplayName("방 삭제")
//    void deleteRoom() {
//        //given
//        List<Room> list = roomTestRepository.findAllRoom();
//        //Room findRoom = roomTestRepository.findByRoomLongId(1L);
//        Room findRoom = list.get(0);
//
//        //when
//        roomTestService.deleteRoom(findRoom.getId());
//        em.flush();
//        em.clear();
//
//        //then
//        findRoom = roomTestRepository.findByRoomLongId(findRoom.getId());
//        assertThat(findMember.isDeleted()).isEqualTo(true);
//    }

}