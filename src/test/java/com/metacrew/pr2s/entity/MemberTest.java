package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.dto.JoinMemberDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {
    @Test
    public void overLoadingTest(){
        JoinMemberDto joinMemberDto = new JoinMemberDto();
        joinMemberDto.setName("노성규");
        joinMemberDto.setPassword("1234");
        joinMemberDto.setLoginId("shtjdrb");
        joinMemberDto.setEmail("gkdlshtjdrb@naver.com");
        joinMemberDto.setTelNo("01012341234");
        Member member = Member.createJoinMember(joinMemberDto);
        Assertions.assertThat(member.getName()).isEqualTo("노성규");
        Assertions.assertThat(member.getPassword()).isEqualTo("1234");
        Assertions.assertThat(member.getLoginId()).isEqualTo("shtjdrb");
        Assertions.assertThat(member.getEmail()).isEqualTo("gkdlshtjdrb@naver.com");
        Assertions.assertThat(member.getTelNo()).isEqualTo("01012341234");
        Assertions.assertThat(member.getAddress()).isNull();
        Assertions.assertThat(member.getImageFile()).isNull();
    }
}