package com.metacrew.pr2s.service.roomservice;

import com.metacrew.pr2s.dto.*;
import com.metacrew.pr2s.entity.*;
import com.metacrew.pr2s.entity.enums.Usage;
import com.metacrew.pr2s.repository.institutionaddressrepository.InstitutionAddressRepository;
import com.metacrew.pr2s.repository.roomtagrepository.RoomTagQueryRepositoryImpl;
import com.metacrew.pr2s.repository.roomusagerepository.RoomUsageQueryRepositoryImpl;
import com.metacrew.pr2s.service.addressservice.InstitutionAddressService;
import com.metacrew.pr2s.service.institutionservice.InstitutionManagerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(value = false)
class RoomManagerServiceTest {
    @Autowired
    private RoomManagerService roomManagerService;
    @Autowired
    private InstitutionManagerService institutionManagerService;
    @Autowired
    private InstitutionAddressService institutionAddressService;
    @Autowired
    private InstitutionAddressRepository institutionAddressRepository;
    @Autowired
    EntityManager em;
    RoomUsageQueryRepositoryImpl roomUsageQueryRepository;
    RoomTagQueryRepositoryImpl roomTagQueryRepository;

    @BeforeEach
    public void init(){
        roomUsageQueryRepository = new RoomUsageQueryRepositoryImpl(em);
        roomTagQueryRepository = new RoomTagQueryRepositoryImpl(em);
    }

    @Test
    @DisplayName("방 삽입 테스트")
    public void registerTest() {
        //given
        Institution insertedInstitution = institutionManagerService.joinPr2s(getTestInstitutionDtoByInsertTestData(), getTestWorkdaysDtoByTestData());
        Address insertAddress = institutionAddressService.saveAddress(getTestAddressDtoByInsertTestData());
        InstitutionAddress insertedInstitutionAddress = institutionAddressRepository.save(InstitutionAddress.createInstitutionAddress(insertedInstitution,insertAddress));

        //when
        Room insertedRoom = roomManagerService.register(getTestRoomDtoByInsertTestData(), insertedInstitutionAddress, getTestRoomUsageDtoListByInsertTestData(), getTestRoomTagDtoListByInsertTestData());
        List<RoomUsage> roomUsages = roomUsageQueryRepository.searchByRoomId(insertedRoom);
        List<RoomTag> roomTags = roomTagQueryRepository.searchByRoomId(insertedRoom);

        //then
        assertThat(insertedRoom.getTitle()).isEqualTo(getTestRoomDtoByInsertTestData().getTitle());
        assertThat(insertedRoom.getInstitutionAddress().getId()).isEqualTo(insertedInstitutionAddress.getId());
        assertThat(roomUsages.size()).isEqualTo(getTestRoomUsageDtoListByInsertTestData().size());
        assertThat(roomTags.size()).isEqualTo(getTestRoomTagDtoListByInsertTestData().size());
    }

    public List<RoomUsageDto> getTestRoomUsageDtoListByInsertTestData() {
        List<RoomUsageDto> roomUsageDtoList = new ArrayList<>();
        RoomUsageDto roomUsageDto1 = new RoomUsageDto();
        roomUsageDto1.setUsage(Usage.PARTY_ROOM);
        RoomUsageDto roomUsageDto2 = new RoomUsageDto();
        roomUsageDto2.setUsage(Usage.CONCERT_ROOM);

        roomUsageDtoList.add(roomUsageDto1);
        roomUsageDtoList.add(roomUsageDto2);

        return roomUsageDtoList;
    }
    
    public List<RoomTagDto> getTestRoomTagDtoListByInsertTestData() {
        List<RoomTagDto> roomUsageDtoList = new ArrayList<>();
        RoomTagDto roomTagDto1 = new RoomTagDto();
        roomTagDto1.setName("아늑함");
        RoomTagDto roomTagDto2 = new RoomTagDto();
        roomTagDto2.setName("신남");

        roomUsageDtoList.add(roomTagDto1);
        roomUsageDtoList.add(roomTagDto2);

        return roomUsageDtoList;
    }

    public RoomDto getTestRoomDtoByInsertTestData() {
        RoomDto roomDto = new RoomDto();
        roomDto.setTitle("방타이틀wwwwwwwww");
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