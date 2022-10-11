package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.dto.*;
import com.metacrew.pr2s.entity.enums.Usage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoomTagTest {

    @Test
    @DisplayName("방 사용용도 생성 테스트")
    public void createRoomUsageTest(){
        //given
        RoomDto roomDto = getTestRoomDtoByInsertTestData();
        Workdays workdays = Workdays.createWorkdays(getTestWorkdaysDtoByTestData());
        Institution institution = Institution.createInstitution(getTestInstitutionDtoByInsertTestData(), workdays);
        Address address = Address.createAddressByAddressDto(getTestAddressDtoByInsertTestData());
        InstitutionAddress institutionAddress = InstitutionAddress.createInstitutionAddress(institution, address);
        Room room = Room.createRoomByRoomDto(roomDto, institutionAddress);

        //when
        RoomTag roomTag = RoomTag.createRoomTag(getTestRoomTagDtoByInsertTestData(), room);

        //then
        assertThat(roomTag.getRoom().getTitle()).isEqualTo("방타이틀");
        assertThat(roomTag.getRoom().getDescription()).isEqualTo("방설명");
        assertThat(roomTag.getRoom().getMaximumPersonCount()).isEqualTo(100);
        assertThat(roomTag.getRoom().getInstitutionAddress()).isEqualTo(institutionAddress);
        assertThat(roomTag.getName()).isEqualTo("아늑함");

    }

    public RoomTagDto getTestRoomTagDtoByInsertTestData() {
        RoomTagDto roomTagDto = new RoomTagDto();
        roomTagDto.setName("아늑함");

        return roomTagDto;
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