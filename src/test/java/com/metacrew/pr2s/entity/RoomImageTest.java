package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.dto.AddressDto;
import com.metacrew.pr2s.dto.InstitutionCreateDto;
import com.metacrew.pr2s.dto.RoomDto;
import com.metacrew.pr2s.dto.WorkdaysDto;
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
        Workdays workdays = Workdays.createWorkdays(getTestWorkdaysDtoByTestData());
        Institution institution = Institution.createInstitution(getTestInstitutionDtoByInsertTestData(), workdays);
        Address address = Address.createAddressByAddressDto(getTestAddressDtoByInsertTestData());
        InstitutionAddress institutionAddress = InstitutionAddress.createInstitutionAddress(institution, address);
        Room room = Room.createRoomByRoomDto(roomDto, institutionAddress);
        FileInfo fileInfo = FileInfo.createFile("file" + 1, "realFile" + 1, "/path/" + 1, "img", 1024L);

        //when
        RoomImage roomImage = RoomImage.createRoomImage(room, fileInfo);

        //then
        assertThat(roomImage.getRoom().equals(room));
        assertThat(roomImage.getFileInfo().equals(fileInfo));
    }

    public RoomDto getTestRoomDtoByInsertTestData() {
        RoomDto roomDto = new RoomDto();
        roomDto.setTitle("방타이틀");
        roomDto.setDescription("방설명");
        roomDto.setMaximumPersonCount(100);

        return roomDto;
    }

    public InstitutionCreateDto getTestInstitutionDtoByInsertTestData() {
        InstitutionCreateDto institutionCreateDto = new InstitutionCreateDto();
        institutionCreateDto.setName("테스트1");
        institutionCreateDto.setTelNumber("010-1234-5678");

        return institutionCreateDto;
    }

    public WorkdaysDto getTestWorkdaysDtoByTestData() {
        WorkdaysDto workdaysDto = new WorkdaysDto();
        workdaysDto.setIsMonday(true);
        workdaysDto.setIsWednesday(true);
        workdaysDto.setIsFriday(true);
        return workdaysDto;
    }

    public AddressDto getTestAddressDtoByInsertTestData() {
        AddressDto testAddressDto = new AddressDto();
        testAddressDto.setRoadFullAddr("서울시 마포구 월드컵북로 434");
        return testAddressDto;
    }

}
