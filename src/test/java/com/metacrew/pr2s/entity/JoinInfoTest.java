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
        joinMemberDto.setLoginId("shtjdrb");
        joinMemberDto.setPassword("shtjdrb123");
        joinMemberDto.setBirthDay("19950914");
        Member member = Member.createJoinMember(joinMemberDto, null, null);

        Workdays workdays = Workdays.createWorkdays(getTestWorkdaysDtoByTestData());

        Address address = Address.createAddressByAddressDto(getTestAddressDtoByTestData());
        InstitutionCreateDto institutionCreateDto = new InstitutionCreateDto();
        institutionCreateDto.setName("우리은행");
        institutionCreateDto.setTelNumber("010-3013-8124");
        Institution institution = Institution.createInstitution(institutionCreateDto, workdays, address);
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

    public AddressDto getTestAddressDtoByTestData() {
        AddressDto addressDto = new AddressDto();
        addressDto.setRn("비고");
        return addressDto;
    }
}