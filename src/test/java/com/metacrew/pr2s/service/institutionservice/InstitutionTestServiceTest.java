package com.metacrew.pr2s.service.institutionservice;

import com.metacrew.pr2s.dto.InstitutionDto;
import com.metacrew.pr2s.dto.WorkdaysDto;
import com.metacrew.pr2s.entity.Institution;
import com.metacrew.pr2s.entity.Workdays;
import com.metacrew.pr2s.repository.institutionrepository.InstitutionTestRepository;
import com.metacrew.pr2s.repository.workdaysrepository.WorkdaysTestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class InstitutionTestServiceTest {
    @Autowired
    private InstitutionTestService institutionTestService;

    @Test
    public void joinPr2sTest() {
        //given
        InstitutionDto institutionDto = getTestInstitutionDtoByTestData();
        WorkdaysDto workdaysDto = getTestWorkdaysDtoByTestData();

        //when
        Institution institution = institutionTestService.joinPr2s(institutionDto, workdaysDto);

        //then
        assertThat(institution.getName()).isEqualTo(institution.getName());
    }

    public InstitutionDto getTestInstitutionDtoByTestData() {
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