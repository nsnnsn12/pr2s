package com.metacrew.pr2s.repository.roomusagerepository;

import com.metacrew.pr2s.dto.*;
import com.metacrew.pr2s.entity.*;
import com.metacrew.pr2s.entity.enums.Usage;
import com.metacrew.pr2s.repository.institutionaddressrepository.InstitutionAddressRepository;
import com.metacrew.pr2s.repository.roomrepository.RoomRepository;
import com.metacrew.pr2s.service.addressservice.InstitutionAddressService;
import com.metacrew.pr2s.service.institutionservice.InstitutionManagerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class RoomUsageQueryRepositoryImplTest {
    @Autowired
    EntityManager em;
    RoomUsageQueryRepositoryImpl roomUsageQueryRepository;
    @Autowired
    RoomUsageRepository roomUsageRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    InstitutionManagerService institutionManagerService;
    @Autowired
    InstitutionAddressService institutionAddressService;
    @Autowired
    InstitutionAddressRepository institutionAddressRepository;

    @BeforeEach
    public void init(){
        roomUsageQueryRepository = new RoomUsageQueryRepositoryImpl(em);
    }

    @Test
    @DisplayName("방 ID로 방 사용용도 검색")
    void searchByRoomIdTest() {
        //given
        Institution insertedInstitution = institutionManagerService.joinPr2s(getTestInstitutionDtoByInsertTestData(), getTestWorkdaysDtoByTestData());
        Address insertAddress = institutionAddressService.saveAddress(getTestAddressDtoByInsertTestData());
        InstitutionAddress insertedInstitutionAddress = institutionAddressRepository.save(InstitutionAddress.createInstitutionAddress(insertedInstitution,insertAddress));
        Room room = roomRepository.save(Room.createRoomByRoomDto(getTestRoomDtoByInsertTestData(), insertedInstitutionAddress));

        for (RoomUsageDto roomUsageDto : getTestRoomUsageListByInsertedData()) {
            roomUsageRepository.save(RoomUsage.createRoomUsage(roomUsageDto, room));
        }

        //when
        List<RoomUsage> roomUsageList = roomUsageQueryRepository.searchByRoomId(room);

        //then
        assertThat(roomUsageList.size()).isEqualTo(getTestRoomUsageListByInsertedData().size());

    }

    public List<RoomUsageDto> getTestRoomUsageListByInsertedData() {
        List<RoomUsageDto> roomUsageDtoList = new ArrayList<>();
        Usage[] usages = new Usage[3];
        usages[0] = Usage.PARTY_ROOM;
        usages[1] = Usage.CONCERT_ROOM;
        usages[2] = Usage.CONFERENCE_ROOM;

        for(int i = 0; i < 3; i++) {
            RoomUsageDto roomUsageDto = new RoomUsageDto();
            roomUsageDto.setUsage(usages[i]);
        }

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