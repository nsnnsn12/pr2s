package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.dto.AddressDto;
import com.metacrew.pr2s.dto.InstitutionCreateDto;
import com.metacrew.pr2s.dto.WorkdaysDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class InstitutionTest {
    @Test
    @DisplayName("기관 생성 테스트")
    public void createInstitutionTest() {
        //given
        InstitutionCreateDto institutionCreateDto = getTestInstitutionDtoByInsertTestData();
        Workdays workdays = Workdays.createWorkdays(getTestWorkdaysDtoByTestData());
        Address address = Address.createAddressByAddressDto(getTestAddressDtoByTestData());

        //when
        Institution institution = Institution.createInstitution(institutionCreateDto, workdays, address);

        //then
        assertThat(institution.getName()).isEqualTo(institutionCreateDto.getName());
        assertThat(institution.getTelNumber()).isEqualTo(institutionCreateDto.getTelNumber());
        assertThat(institution.getWorkdays().getIsMonday()).isEqualTo(workdays.getIsMonday());
        assertThat(institution.getWorkdays().getIsWednesday()).isEqualTo(workdays.getIsWednesday());
        assertThat(institution.getWorkdays().getIsFriday()).isEqualTo(workdays.getIsFriday());
        assertThat(institution.isApprovedRegistration()).isEqualTo(false);

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

    public AddressDto getTestAddressDtoByTestData() {
        AddressDto addressDto = new AddressDto();
        addressDto.setRn("비고");
        return addressDto;
    }
}