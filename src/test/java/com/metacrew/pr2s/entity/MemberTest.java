package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.dto.JoinMemberDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MemberTest {
    @Test
    public void overLoadingTest(){
        //given
        JoinMemberDto joinMemberDto = new JoinMemberDto();
        joinMemberDto.setName("노성규");
        joinMemberDto.setPassword("1234");
        joinMemberDto.setLoginId("shtjdrb");
        joinMemberDto.setEmail("gkdlshtjdrb@naver.com");
        joinMemberDto.setTelNo("01012341234");

        //when
        Member member = Member.createJoinMember(joinMemberDto);

        //then
        assertThat(member.getName()).isEqualTo("노성규");
        assertThat(member.getPassword()).isEqualTo("1234");
        assertThat(member.getLoginId()).isEqualTo("shtjdrb");
        assertThat(member.getEmail()).isEqualTo("gkdlshtjdrb@naver.com");
        assertThat(member.getTelNo()).isEqualTo("01012341234");
        assertThat(member.getAddress()).isNull();
        assertThat(member.getImageFile()).isNull();
    }
}