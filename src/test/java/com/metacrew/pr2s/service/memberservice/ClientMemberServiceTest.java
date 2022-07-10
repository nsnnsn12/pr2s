package com.metacrew.pr2s.service.memberservice;

import com.metacrew.pr2s.dto.AddressDto;
import com.metacrew.pr2s.dto.JoinMemberDto;
import com.metacrew.pr2s.dto.MyPageDto;
import com.metacrew.pr2s.entity.Member;
import com.metacrew.pr2s.repository.memberrepository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class ClientMemberServiceTest {
    @Autowired
    private ClientMemberService clientMemberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    EntityManager entityManager;

    private static final String EXISTED_LOGIN_ID = "kqrgusdn";
    @BeforeEach
    public void init(){
        JoinMemberDto joinMemberDto = new JoinMemberDto();
        joinMemberDto.setName("박현우");
        joinMemberDto.setLoginId(EXISTED_LOGIN_ID);
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
        joinMemberDto.setLoginId(EXISTED_LOGIN_ID);
        AddressDto addressDto = new AddressDto();
        addressDto.setSggNm("서울시 노원구");

        assertThatThrownBy(() -> {
            Member member = clientMemberService.join(joinMemberDto, addressDto);
        }).isInstanceOf(IllegalStateException.class).hasMessageContaining("이미 존재하는 회원입니다.");
    }

    @Test
    @DisplayName("마이페이지 정보 조회")
    void getMyPageInfo() {
        // given
        Member findMember = memberRepository.findByLoginId(EXISTED_LOGIN_ID).orElseThrow();

        // when
        MyPageDto myPageInfo = clientMemberService.getMyPageInfo(findMember.getId());
        // then
        assertThat(myPageInfo.getName()).isEqualTo(findMember.getName());
        assertThat(myPageInfo.getEmail()).isEqualTo(findMember.getEmail());
        assertThat(myPageInfo.getTelNo()).isEqualTo(findMember.getTelNo());
        assertThat(myPageInfo.getBirthDay()).isEqualTo(findMember.getBirthDay());
    }

    @Test
    @DisplayName("존재하지 않는 마이페이지 정보 조회")
    void getMyPageInfoByNonMemberId() {
        // then
        assertThatThrownBy(() -> {
            MyPageDto myPageInfo = clientMemberService.getMyPageInfo(-1L);
        }).isInstanceOf(IllegalStateException.class).hasMessageContaining("존재하지 않는 회원정보입니다.");
    }

    @Test
    @DisplayName("마이페이지 정보 수정")
    void updateForMyPage() {
        //given
        JoinMemberDto joinMemberDto = new JoinMemberDto();
        joinMemberDto.setName("노성규");
        joinMemberDto.setLoginId("shtjdrb");
        joinMemberDto.setPassword("shtjdrb123");
        joinMemberDto.setBirthDay("19950914");

        AddressDto addressDto = new AddressDto();
        addressDto.setSggNm("서울시 마포구");

        Member member = clientMemberService.join(joinMemberDto, addressDto);
        entityManager.flush();
        entityManager.clear();

        //when
        MyPageDto myPageDto = new MyPageDto();
        myPageDto.setEmail("gkdlshtjdrb@naver.com");
        myPageDto.setName("박현우");
        myPageDto.setTelNo("01012341234");
        myPageDto.setBirthDay("19950101");
        Long updateId = clientMemberService.updateForMyPage(myPageDto, member.getId());
        entityManager.flush();
        entityManager.clear();

        // then
        Member findMember = memberRepository.findById(member.getId()).orElseThrow();
        assertThat(findMember.getEmail()).isEqualTo(myPageDto.getEmail());
        assertThat(findMember.getName()).isEqualTo(myPageDto.getName());
        assertThat(findMember.getBirthDay()).isEqualTo(myPageDto.getBirthDay());
        assertThat(findMember.getTelNo()).isEqualTo(myPageDto.getTelNo());
    }

    @Test
    @DisplayName("존재하지 않는 마이페이지 수정")
    void updateForMyPageByNonMemberId() {
        // then
        assertThatThrownBy(() -> clientMemberService.updateForMyPage(new MyPageDto(), -1L))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("존재하지 않는 회원정보입니다.");
    }

    @Test
    @DisplayName("회원탈퇴")
    void removeAccount() {
        // given
        JoinMemberDto joinMemberDto = new JoinMemberDto();
        joinMemberDto.setName("노성규");
        joinMemberDto.setLoginId("shtjdrb");
        joinMemberDto.setPassword("shtjdrb123");
        joinMemberDto.setBirthDay("19950914");

        AddressDto addressDto = new AddressDto();
        addressDto.setSggNm("서울시 마포구");

        Member member = clientMemberService.join(joinMemberDto, addressDto);
        entityManager.flush();
        entityManager.clear();
        // when
        clientMemberService.removeAccount(member.getId());
        entityManager.flush();
        entityManager.clear();
        Member findMember = memberRepository.findById(member.getId()).orElseThrow();
        // then
        assertThat(findMember.isDeleted()).isEqualTo(true);
    }

    @Test
    @DisplayName("존재하지 않는 회원정보 탈퇴")
    void removeAccountByNonMemberId() {
        // then
        assertThatThrownBy(() -> clientMemberService.removeAccount( -1L))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("존재하지 않는 회원정보입니다.");
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