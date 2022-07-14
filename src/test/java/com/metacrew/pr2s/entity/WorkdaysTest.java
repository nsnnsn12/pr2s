package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.dto.WorkdaysDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class WorkdaysTest {
    @Test
    @DisplayName("운영요일 생성 테스트")
    public void createWorkdaysTest() {
        //given
        WorkdaysDto workdaysDto = getTestWorkdaysDtoByTestData();

        //when
        Workdays workdays = Workdays.createWorkdays(workdaysDto);

        //when
        assertThat(workdays.getIsMonday()).isEqualTo(workdaysDto.getIsMonday());
        assertThat(workdays.getIsWednesday()).isEqualTo(workdaysDto.getIsWednesday());
        assertThat(workdays.getIsFriday()).isEqualTo(workdaysDto.getIsFriday());
    }

    public WorkdaysDto getTestWorkdaysDtoByTestData() {
        WorkdaysDto workdaysDto = new WorkdaysDto();
        workdaysDto.setIsMonday(true);
        workdaysDto.setIsWednesday(true);
        workdaysDto.setIsFriday(true);
        return workdaysDto;
    }
}