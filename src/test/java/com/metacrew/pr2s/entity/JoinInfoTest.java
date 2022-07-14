package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.dto.InstitutionDto;
import com.metacrew.pr2s.dto.JoinMemberDto;
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

        InstitutionDto institutionDto = new InstitutionDto();
        institutionDto.setName("우리은행");
        institutionDto.setTelNumber("010-3013-8124");
        Institution institution = new Institution(institutionDto);
        // when
        JoinInfo joinInfo = JoinInfo.createJoinInfo(member, institution);

        //then
        assertThat(joinInfo.getMember()).isEqualTo(member);
        assertThat(joinInfo.getInstitution()).isEqualTo(institution);
    }
}