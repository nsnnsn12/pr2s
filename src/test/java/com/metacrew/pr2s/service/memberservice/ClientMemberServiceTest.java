package com.metacrew.pr2s.service.memberservice;

import com.metacrew.pr2s.dto.AddressDto;
import com.metacrew.pr2s.dto.InstitutionDto;
import com.metacrew.pr2s.dto.JoinMemberDto;
import com.metacrew.pr2s.dto.MyPageDto;
import com.metacrew.pr2s.entity.Institution;
import com.metacrew.pr2s.entity.JoinInfo;
import com.metacrew.pr2s.entity.Member;
import com.metacrew.pr2s.repository.institutionrepository.InstitutionRepository;
import com.metacrew.pr2s.repository.joinforepository.JoinInfoRepository;
import com.metacrew.pr2s.repository.memberrepository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class ClientMemberServiceTest {
    @Autowired
    private ClientMemberService clientMemberService;

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private InstitutionRepository institutionRepository;
    @Autowired
    private JoinInfoRepository joinInfoRepository;

    @Autowired
    EntityManager entityManager;

    @BeforeEach
    public void init(){
        JoinMemberDto joinMemberDto = new JoinMemberDto();
        joinMemberDto.setName("박현우");
        joinMemberDto.setLoginId("kqrgusdn");
        joinMemberDto.setPassword("qkrgusdn123");
        joinMemberDto.setBirthDay("19950101");

        AddressDto addressDto = new AddressDto();
        addressDto.setSggNm("서울시 마포구");

        Member member = clientMemberService.join(joinMemberDto, addressDto);

        InstitutionDto institutionDto = new InstitutionDto();
        institutionDto.setName("우리은행");
        institutionDto.setTelNumber("010-3013-8124");
        Institution institution = new Institution(institutionDto);
        institutionRepository.save(institution);
    }

    @Test
    @DisplayName("회원가입")
    void join() {
        // given
        JoinMemberDto joinMemberDto = new JoinMemberDto();
        joinMemberDto.setName("노성규");
        joinMemberDto.setLoginId("shtjdrb");
        joinMemberDto.setPassword("shtjdrb123");
        joinMemberDto.setBirthDay("19950914");

        AddressDto addressDto = new AddressDto();
        addressDto.setSggNm("서울시 노원구");
        Member member = clientMemberService.join(joinMemberDto, addressDto);

        entityManager.flush();
        entityManager.clear();
        // when
        Member findMember = memberRepository.findById(member.getId()).orElseThrow(() -> new IllegalArgumentException("테스트 실패"));

        // then
        assertThat(findMember.getName()).isEqualTo(joinMemberDto.getName());
        assertThat(findMember.getLoginId()).isEqualTo(joinMemberDto.getLoginId());
        assertThat(findMember.getPassword()).isEqualTo(joinMemberDto.getPassword());
        assertThat(findMember.getBirthDay()).isEqualTo(joinMemberDto.getBirthDay());
        assertThat(findMember.getAddress().getSggNm()).isEqualTo(addressDto.getSggNm());
    }

    @Test
    @DisplayName("중복Id로 회원가입")
    void joinByDuplicatedLoginId() {
        // given
        List<Member> list = memberRepository.findAll();
        String duplicatedLoginId = list.get(0).getLoginId();
        JoinMemberDto joinMemberDto = new JoinMemberDto();
        joinMemberDto.setName("노성규");
        joinMemberDto.setLoginId(duplicatedLoginId);
        joinMemberDto.setPassword("shtjdrb123");
        joinMemberDto.setBirthDay("19950914");

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
        List<Member> list = memberRepository.findAll();
        Member findMember = list.get(0);

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
        List<Member> list = memberRepository.findAll();
        Member findMember = list.get(0);

        //when
        MyPageDto myPageDto = new MyPageDto();
        myPageDto.setEmail("gkdlshtjdrb@naver.com");
        myPageDto.setName("박현우");
        myPageDto.setTelNo("01012341234");
        myPageDto.setBirthDay("19950101");
        clientMemberService.updateForMyPage(myPageDto, findMember.getId());
        entityManager.flush();
        entityManager.clear();

        // then
        findMember = memberRepository.findById(findMember.getId()).orElseThrow(() -> new IllegalArgumentException("테스트 실패"));
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
        List<Member> list = memberRepository.findAll();
        Member findMember = list.get(0);

        // when
        clientMemberService.removeAccount(findMember.getId());
        entityManager.flush();
        entityManager.clear();
        findMember = memberRepository.findById(findMember.getId()).orElseThrow(() -> new IllegalArgumentException("테스트 실패"));
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

    @Test
    @DisplayName("기관 가입 요청")
    void requestJoinOfInstitution() {
        //given
        List<Member> list = memberRepository.findAll();
        List<Institution> institutions = institutionRepository.findAll();
        // when
        Long joinInfoId = clientMemberService.requestJoinOfInstitution(list.get(0).getId(), institutions.get(0).getId());
        entityManager.flush();
        entityManager.clear();
        JoinInfo findJoinInfo = joinInfoRepository.findById(joinInfoId).orElseThrow((() -> new IllegalArgumentException("테스트 실패")));
        Member member = memberRepository.findById(findJoinInfo.getMember().getId()).orElseThrow((() -> new IllegalArgumentException("테스트 실패")));
        Institution institution = institutionRepository.findById(findJoinInfo.getInstitution().getId()).orElseThrow((() -> new IllegalArgumentException("테스트 실패")));
        // then
        assertThat(findJoinInfo.getMember()).isEqualTo(member);
        assertThat(findJoinInfo.getInstitution()).isEqualTo(institution);
    }

    @Test
    @DisplayName("회원 정보 없이 기관 가입 요청")
    void requestJoinOfInstitution2() {
        //given
        List<Institution> institutions = institutionRepository.findAll();
        // when
        assertThatThrownBy(() -> clientMemberService.requestJoinOfInstitution(-1L, institutions.get(0).getId()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("존재하지 않는 회원정보입니다.");
    }

    @Test
    @DisplayName("기관 정보 없이 기관 가입 요청")
    void requestJoinOfInstitution3() {
        //given
        List<Member> list = memberRepository.findAll();
        // when
        assertThatThrownBy(() -> clientMemberService.requestJoinOfInstitution(list.get(0).getId(), 1L))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("기관정보가 존재하지 않습니다.");
    }

    @Test
    @DisplayName("중복 기관 가입 요청")
    void requestJoinOfInstitution4() {
        //given
        List<Member> list = memberRepository.findAll();
        List<Institution> institutions = institutionRepository.findAll();
        Long joinInfoId = clientMemberService.requestJoinOfInstitution(list.get(0).getId(), institutions.get(0).getId());
        entityManager.flush();
        entityManager.clear();

        // when
        // then
        assertThatThrownBy(() -> clientMemberService.requestJoinOfInstitution(list.get(0).getId(), institutions.get(0).getId()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("이미 존재하는 가입 요청입니다.");
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