package com.metacrew.pr2s.repository.memberrepository;

import com.metacrew.pr2s.dto.JoinMemberDto;
import com.metacrew.pr2s.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("로그인 아이디를 이용한 조회 테스트")
    void findByLoginId() {
        // given
        Member joinMember = Member.createJoinMember(getJoinMemberDtoByTestData(), null, null);
        Member member = memberRepository.save(joinMember);
        // when
        Optional<Member> findMember = memberRepository.findByLoginId("shtjdrb");
        // then
        assertThat(findMember.isPresent()).isEqualTo(true);
    }

    public JoinMemberDto getJoinMemberDtoByTestData(){
        JoinMemberDto joinMemberDto = new JoinMemberDto();
        joinMemberDto.setName("노성규");
        joinMemberDto.setLoginId("shtjdrb");
        joinMemberDto.setPassword("shtjdrb123");
        joinMemberDto.setBirthDay("19950914");
        return joinMemberDto;
    }
}