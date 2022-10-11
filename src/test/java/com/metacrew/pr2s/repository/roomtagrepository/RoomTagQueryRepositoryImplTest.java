package com.metacrew.pr2s.repository.roomtagrepository;

import com.metacrew.pr2s.dto.*;
import com.metacrew.pr2s.entity.*;
import com.metacrew.pr2s.entity.enums.Usage;
import com.metacrew.pr2s.repository.institutionaddressrepository.InstitutionAddressRepository;
import com.metacrew.pr2s.repository.roomrepository.RoomRepository;
import com.metacrew.pr2s.repository.roomusagerepository.RoomUsageQueryRepositoryImpl;
import com.metacrew.pr2s.repository.roomusagerepository.RoomUsageRepository;
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

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class RoomTagQueryRepositoryImplTest {
    @Autowired
    EntityManager em;
    RoomTagQueryRepositoryImpl roomTagQueryRepository;
    @Autowired
    RoomTagRepository roomTagRepository;
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
        roomTagQueryRepository = new RoomTagQueryRepositoryImpl(em);
    }

    @Test
    @DisplayName("방 ID로 방 태그 검색")
    void searchByRoomIdTest() {
        //given
        Institution insertedInstitution = institutionManagerService.joinPr2s(getTestInstitutionDtoByInsertTestData(), getTestWorkdaysDtoByTestData());
        Address insertAddress = institutionAddressService.saveAddress(getTestAddressDtoByInsertTestData());
        InstitutionAddress insertedInstitutionAddress = institutionAddressRepository.save(InstitutionAddress.createInstitutionAddress(insertedInstitution,insertAddress));
        Room room = roomRepository.save(Room.createRoomByRoomDto(getTestRoomDtoByInsertTestData(), insertedInstitutionAddress));

        for (RoomTagDto roomTagDto : getTestRoomTagListByInsertedData()) {
            roomTagRepository.save(RoomTag.createRoomTag(roomTagDto, room));
        }

        //when
        List<RoomTag> roomTagList = roomTagQueryRepository.searchByRoomId(room);

        //then
        assertThat(roomTagList.size()).isEqualTo(getTestRoomTagListByInsertedData().size());

    }

    public List<RoomTagDto> getTestRoomTagListByInsertedData() {
        List<RoomTagDto> roomTagDtoList = new ArrayList<>();
        String[] names = new String[3];
        names[0] = "아늑함";
        names[1] = "신남";
        names[2] = "MZ";

        for(int i = 0; i < 3; i++) {
            RoomTagDto roomTagDto = new RoomTagDto();
            roomTagDto.setName(names[i]);
            roomTagDtoList.add(roomTagDto);
        }

        return roomTagDtoList;
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