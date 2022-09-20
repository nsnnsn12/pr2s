package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.dto.AddressDto;
import com.metacrew.pr2s.dto.InstitutionCreateDto;
import com.metacrew.pr2s.dto.WorkdaysDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class InstitutionAddressTest {
    @Test
    @DisplayName("기관주소정보 생성 테스트")
    public void createInstitutionAddressTest() {
        //given
        Workdays workdays = Workdays.createWorkdays(getTestWorkdaysDtoByTestData());

        Institution institution = Institution.createInstitution(getTestInstitutionDtoByInsertTestData(), workdays);
        Address address = Address.createAddressByAddressDto(getTestAddressDtoByInsertTestData());

        //when
        InstitutionAddress institutionAddress = InstitutionAddress.createInstitutionAddress(institution, address);

        //then
        assertThat(institutionAddress.getInstitution()).isEqualTo(institution);
        assertThat(institutionAddress.getAddress()).isEqualTo(address);

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