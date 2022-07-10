package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.dto.InstitutionDto;
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
        InstitutionDto institutionDto = getTestInstitutionDtoByInsertTestData();
        WorkdaysDto workdaysDto = getTestWorkdaysDtoByTestData();
        Workdays workdays = Workdays.createWorkdays(workdaysDto);

        //when
        Institution institution = Institution.createInstitution(institutionDto, workdays);

        //then
        assertThat(institution.getName()).isEqualTo(institutionDto.getName());
        assertThat(institution.getTelNumber()).isEqualTo(institutionDto.getTelNumber());
        assertThat(institution.getWorkdays().getIsMonday()).isEqualTo(workdays.getIsMonday());
        assertThat(institution.getWorkdays().getIsWednesday()).isEqualTo(workdays.getIsWednesday());
        assertThat(institution.getWorkdays().getIsFriday()).isEqualTo(workdays.getIsFriday());

    }

    public InstitutionDto getTestInstitutionDtoByInsertTestData() {
        InstitutionDto institutionDto = new InstitutionDto();
        institutionDto.setName("테스트1");
        institutionDto.setTelNumber("010-1234-5678");

        return institutionDto;
    }

    public WorkdaysDto getTestWorkdaysDtoByTestData() {
        WorkdaysDto workdaysDto = new WorkdaysDto();
        workdaysDto.setIsMonday(true);
        workdaysDto.setIsWednesday(true);
        workdaysDto.setIsFriday(true);
        return workdaysDto;
    }
}