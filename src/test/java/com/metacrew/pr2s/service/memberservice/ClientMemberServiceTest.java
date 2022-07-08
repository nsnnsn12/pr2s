package com.metacrew.pr2s.service.memberservice;

import com.metacrew.pr2s.dto.AddressDto;
import com.metacrew.pr2s.dto.JoinMemberDto;
import com.metacrew.pr2s.dto.MyPageDto;
import com.metacrew.pr2s.entity.Member;
import com.metacrew.pr2s.repository.memberrepository.MemberRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class ClientMemberServiceTest {
    @Autowired
    private ClientMemberService clientMemberService;

    @Autowired
    private MemberRepository memberRepository;

    private static final String DUPLICATE_LOGIN_ID = "kqrgusdn";
    @BeforeEach
    public void init(){
        JoinMemberDto joinMemberDto = new JoinMemberDto();
        joinMemberDto.setName("박현우");
        joinMemberDto.setLoginId(DUPLICATE_LOGIN_ID);
        joinMemberDto.setPassword("qkrgusdn123");
        joinMemberDto.setBirthDay("19950101");

        AddressDto addressDto = new AddressDto();
        addressDto.setSggNm("서울시 마포구");

        Member member = clientMemberService.join(joinMemberDto, addressDto);
    }

    @Test
    @DisplayName("중복 id를 사용하지 않는 회원가입")
    void join() {
        // given
        JoinMemberDto joinMemberDto = getJoinMemberDtoByTestData();
        joinMemberDto.setLoginId("shtjdrb123");
        AddressDto addressDto = new AddressDto();
        addressDto.setSggNm("서울시 노원구");
        Member member = clientMemberService.join(joinMemberDto, addressDto);

        // when
        Optional<Member> findMember = memberRepository.findById(member.getId());

        // then
        assertThat(findMember.isPresent()).isEqualTo(true);
    }

    @Test
    @DisplayName("중복Id로 회원가입")
    void joinByDuplicatedLoginId() {
        // given
        JoinMemberDto joinMemberDto = getJoinMemberDtoByTestData();
        joinMemberDto.setLoginId("kqrgusdn");
        AddressDto addressDto = new AddressDto();
        addressDto.setSggNm("서울시 노원구");

        assertThatThrownBy(() -> {
            Member member = clientMemberService.join(joinMemberDto, addressDto);
        }).isInstanceOf(IllegalStateException.class).hasMessageContaining("이미 존재하는 회원입니다.");
    }

    @Test
    @DisplayName("마이페이지 정보 조회 테스트")
    void getMyPageInfo() {
        // given
        Member findMember = memberRepository.findByLoginId("kqrgusdn").orElseThrow();

        // when
        MyPageDto myPageInfo = clientMemberService.getMyPageInfo(findMember.getId());
        // then
        assertThat(myPageInfo.getName()).isEqualTo(findMember.getName());
        assertThat(myPageInfo.getEmail()).isEqualTo(findMember.getEmail());
        assertThat(myPageInfo.getTelNo()).isEqualTo(findMember.getTelNo());
        assertThat(myPageInfo.getBirthDay()).isEqualTo(findMember.getBirthDay());
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