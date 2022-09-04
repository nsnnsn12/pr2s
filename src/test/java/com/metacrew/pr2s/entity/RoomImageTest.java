package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.dto.AddressDto;
import com.metacrew.pr2s.dto.RoomDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RoomImageTest {

    @Test
    @DisplayName("@@@방사진@@@")
    public void createRoomImageTest(){
        //given
        RoomDto roomDto = getTestRoomDtoByInsertTestData();
        Address address = Address.createAddressByAddressDto(getTestAddressDtoByInsertTestData());
        List<FileInfo> fileInfoList = new ArrayList<>();

        for (int i = 1; i < 3; i++) {
            FileInfo fileInfo = FileInfo.createFile("name", "source",null);
            fileInfoList.add(fileInfo);
        }

        //when
        Room room = Room.createRoomByRoomDto(roomDto, address);
        RoomImage roomImage = RoomImage.createRoomImage(room, fileInfoList.get(0));

        //then
        assertThat(room.getTitle()).isEqualTo("방타이틀");
        assertThat(room.getDescription()).isEqualTo("방설명");
        assertThat(room.getMaximumPersonCount()).isEqualTo(100);
        assertThat(room.getAddress().getRn().equals(address.getRn()));
        //assertThat(room.getFileList().size()).isEqualTo(2);

        assertThat(roomImage.getRoom().equals(room));
    }


    public RoomDto getTestRoomDtoByInsertTestData() {
        RoomDto roomDto = new RoomDto();
        roomDto.setTitle("방타이틀");
        roomDto.setDescription("방설명");
        roomDto.setMaximumPersonCount(100);

        return roomDto;
    }

    public AddressDto getTestAddressDtoByInsertTestData(){
        AddressDto addressDto = new AddressDto();
        addressDto.setRn("비고");

        return addressDto;
    }

}
