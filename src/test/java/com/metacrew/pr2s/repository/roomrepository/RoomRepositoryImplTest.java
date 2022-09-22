package com.metacrew.pr2s.repository.roomrepository;

import com.metacrew.pr2s.dto.AddressDto;
import com.metacrew.pr2s.dto.InstitutionCreateDto;
import com.metacrew.pr2s.dto.RoomDto;
import com.metacrew.pr2s.dto.WorkdaysDto;
import com.metacrew.pr2s.entity.*;
import com.metacrew.pr2s.repository.AddressRepository;
import com.metacrew.pr2s.repository.institutionaddressrepository.InstitutionAddressRepository;
import com.metacrew.pr2s.repository.institutionrepository.InstitutionRepository;
import com.metacrew.pr2s.repository.workdaysrepository.WorkdaysRepository;
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
class RoomRepositoryImplTest {
    @Autowired
    private InstitutionRepository institutionRepository;
    @Autowired
    private WorkdaysRepository workdaysRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private InstitutionAddressRepository institutionAddressRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomRepositoryImpl roomRepositoryImpl;

    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("제목을 이용한 조회 테스트")
    void findByRoomTitle(){
        //given
        Workdays workdays = Workdays.createWorkdays(getTestWorkdaysDtoByTestData());
        workdaysRepository.save(workdays);

        Institution institution = Institution.createInstitution(getTestInstitutionDtoByInsertTestData(), workdays);
        institutionRepository.save(institution);

        Address address = Address.createAddressByAddressDto(getTestAddressDtoByInsertTestData());
        addressRepository.save(address);

        InstitutionAddress institutionAddress = InstitutionAddress.createInstitutionAddress(institution, address);
        institutionAddressRepository.save(institutionAddress);

        Room room = Room.createRoomByRoomDto(getTestRoomDtoByInsertTestData(), institutionAddress);
        roomRepository.save(room);

        //when
        List<Room> findRooms = roomRepositoryImpl.findByRoomTitle("방타이틀wwwwwwwww");

        //then
        for(Room findRoom : findRooms) {
            assertThat(findRoom.getTitle()).isEqualTo("방타이틀wwwwwwwww");
        }
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