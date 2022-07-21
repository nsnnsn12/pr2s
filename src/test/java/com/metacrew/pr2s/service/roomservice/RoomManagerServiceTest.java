package com.metacrew.pr2s.service.roomservice;

import com.metacrew.pr2s.dto.AddressDto;
import com.metacrew.pr2s.dto.RoomDto;
import com.metacrew.pr2s.entity.Address;
import com.metacrew.pr2s.entity.Room;
import com.metacrew.pr2s.repository.AddressRepository;
import com.metacrew.pr2s.repository.roomrepository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(value = false)
class RoomManagerServiceTest {

    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomManagerService roomManagerService;
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private EntityManager em;

    @BeforeEach
    public void init() {
        RoomDto roomDto = new RoomDto();
        roomDto.setTitle("초기화시 생성된 방");
        roomDto.setDescription("초기화 방이다");
        roomDto.setMaximumPersonCount(50);
        AddressDto addressDto = getTestAddressDtoByTestData();
        Room room = roomManagerService.register(roomDto,addressDto);
    }


    @Test
    @DisplayName("registerRoom without validation 중복체크 없이 방 등록")
    void registerRoom() {
        //given
        RoomDto roomDto = getRoomDtoTestData();
        AddressDto addressDto = getTestAddressDtoByTestData();

        Room room = roomManagerService.register(roomDto,addressDto);
        em.flush();
        em.clear();

        //when
        Room findRoom = roomRepository.findByRoomId(room.getId());

        //then
        assertThat(findRoom.getTitle()).isEqualTo(room.getTitle());
    }

    //dto 설정
    public RoomDto getRoomDtoTestData() {
        RoomDto roomDto =new RoomDto();
        roomDto.setTitle("방이름");
        roomDto.setDescription("방설명");
        roomDto.setMaximumPersonCount(100);
        return roomDto;
    }

    public AddressDto getTestAddressDtoByTestData() {
        AddressDto addressDto = new AddressDto();
        addressDto.setRn("비고");
        return addressDto;
    }

}