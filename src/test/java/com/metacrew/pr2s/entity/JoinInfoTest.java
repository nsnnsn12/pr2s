package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.dto.AddressDto;
import com.metacrew.pr2s.dto.InstitutionCreateDto;
import com.metacrew.pr2s.dto.JoinMemberDto;
import com.metacrew.pr2s.dto.WorkdaysDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class JoinInfoTest {
    @Test
    @DisplayName("JoinMember 생성")
    void createJoinInfo() {
        // given
        JoinMemberDto joinMemberDto = new JoinMemberDto();
        joinMemberDto.setName("노성규");
        joinMemberDto.setEmail("shtjdrb");
        joinMemberDto.setPassword("shtjdrb123");
        Member member = Member.createJoinMember(joinMemberDto);

        InstitutionCreateDto institutionCreateDto = new InstitutionCreateDto();
        institutionCreateDto.setName("우리은행");
        institutionCreateDto.setTelNumber("010-3013-8124");
        Workdays workdays = Workdays.createWorkdays(getTestWorkdaysDtoByTestData());
        Institution institution = Institution.createInstitution(institutionCreateDto, workdays);

        // when
        JoinInfo joinInfo = JoinInfo.createJoinInfo(member, institution);

        //then
        assertThat(joinInfo.getMember()).isEqualTo(member);
        assertThat(joinInfo.getInstitution()).isEqualTo(institution);
    }

    public WorkdaysDto getTestWorkdaysDtoByTestData() {
        WorkdaysDto workdaysDto = new WorkdaysDto();
        workdaysDto.setIsMonday(true);
        workdaysDto.setIsWednesday(true);
        workdaysDto.setIsFriday(true);
        return workdaysDto;
    }
}