package com.metacrew.pr2s.service.memberservice;

import com.metacrew.pr2s.dto.*;
import com.metacrew.pr2s.entity.*;
import com.metacrew.pr2s.repository.FileInfoRepository;
import com.metacrew.pr2s.repository.institutionrepository.InstitutionRepository;
import com.metacrew.pr2s.repository.joininforepository.JoinInfoRepository;
import com.metacrew.pr2s.repository.memberrepository.MemberRepository;
import com.metacrew.pr2s.repository.workdaysrepository.WorkdaysRepository;
import com.metacrew.pr2s.service.institutionservice.InstitutionService;
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
    private WorkdaysRepository workdaysRepository;
    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private FileInfoRepository fileInfoRepository;
    @Autowired
    EntityManager entityManager;
    @Test
    @DisplayName("회원가입시 프로필을 등록하지 않은 경우")
    void join() {
        // given
        JoinMemberDto joinMemberDto = getJoinMemberDtoByTestData();

        AddressDto addressDto = getAddressDtoByTestData();
        Member member = clientMemberService.join(joinMemberDto, 1L, 11L);
        entityManager.flush();
        entityManager.clear();

        // when
        Member findMember = memberRepository.findById(member.getId()).orElseThrow(() -> new IllegalArgumentException("테스트 실패"));

        // then
        assertThat(findMember.getName()).isEqualTo(joinMemberDto.getName());
        assertThat(findMember.getEmail()).isEqualTo(joinMemberDto.getEmail());
        assertThat(findMember.getPassword()).isEqualTo(joinMemberDto.getPassword());
        assertThat(findMember.getBirthDay()).isEqualTo(joinMemberDto.getBirthDay());
        assertThat(findMember.getAddress().getSggNm()).isEqualTo(addressDto.getSggNm());
    }

    @Test
    @DisplayName("중복Id로 회원가입하는 경우")
    void joinByDuplicatedLoginId() {
        // given
        List<Member> list = memberRepository.findAll();
        String duplicatedEmail = list.get(0).getEmail();
        JoinMemberDto joinMemberDto = getJoinMemberDtoByTestData();
        joinMemberDto.setEmail(duplicatedEmail);

        AddressDto addressDto = getAddressDtoByTestData();

        assertThatThrownBy(() -> {
            Member member = clientMemberService.join(joinMemberDto, 1L, 11L);
        }).isInstanceOf(IllegalStateException.class).hasMessageContaining("이미 존재하는 이메일입니다.");
    }

    @Test
    @DisplayName("회원가입시 프로필을 등록하는 경우")
    void joinExistedFile() {
        // given
        JoinMemberDto joinMemberDto = getJoinMemberDtoByTestData();

        AddressDto addressDto = getAddressDtoByTestData();
        FileInfo testFileInfo = getTestFile();
        fileInfoRepository.save(testFileInfo);
        Member member = clientMemberService.join(joinMemberDto, 1L, testFileInfo.getId());
        entityManager.flush();
        entityManager.clear();

        // when
        Member findMember = memberRepository.findById(member.getId()).orElseThrow(() -> new IllegalArgumentException("테스트 실패"));

        // then
        assertThat(findMember.getName()).isEqualTo(joinMemberDto.getName());
        assertThat(findMember.getEmail()).isEqualTo(joinMemberDto.getEmail());
        assertThat(findMember.getPassword()).isEqualTo(joinMemberDto.getPassword());
        assertThat(findMember.getBirthDay()).isEqualTo(joinMemberDto.getBirthDay());
        assertThat(findMember.getAddress().getSggNm()).isEqualTo(addressDto.getSggNm());
    }

    @Test
    @DisplayName("회원가입시 존재하지 않는 파일의 대한 예외 처리")
    void joinFileException() {
        // given
        JoinMemberDto joinMemberDto = getJoinMemberDtoByTestData();

        AddressDto addressDto = getAddressDtoByTestData();
        // when
        assertThatThrownBy(() -> {
            Member member = clientMemberService.join(joinMemberDto, 1L, -1L);
        }).isInstanceOf(IllegalStateException.class).hasMessageContaining("존재하지 않는 파일정보입니다.");
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
    @DisplayName("회원탈퇴한 회원의 관한 마이페이지 정보 조회")
    void getMyPageInfoByDeletedMember() {
        // given
        List<Member> list = memberRepository.findAll();
        Member findMember = list.get(0);
        findMember.deleted();
        entityManager.flush();
        entityManager.clear();
        // then
        assertThatThrownBy(() -> {
            MyPageDto myPageInfo = clientMemberService.getMyPageInfo(findMember.getId());
        }).isInstanceOf(IllegalStateException.class).hasMessageContaining("존재하지 않는 회원정보입니다.");
    }

    @Test
    @DisplayName("마이페이지 정보 수정")
    void updateForMyPage() {
        //given
        List<Member> list = memberRepository.findAll();
        Member findMember = list.get(0);

        //when
        MyPageDto myPageDto = getMyPageDto();
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
    @DisplayName("회원탈퇴한 회원의 관한 마이페이지 수정")
    void updateForMyPageByDeletedMember() {
        // given
        List<Member> list = memberRepository.findAll();
        Member findMember = list.get(0);
        findMember.deleted();
        entityManager.flush();
        entityManager.clear();
        // then
        assertThatThrownBy(() -> clientMemberService.updateForMyPage(new MyPageDto(), findMember.getId()))
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
    @DisplayName("회원탈퇴한 회원정보 탈퇴")
    void removeAccountByDeletedMember() {
        // given
        List<Member> list = memberRepository.findAll();
        Member findMember = list.get(0);
        findMember.deleted();
        entityManager.flush();
        entityManager.clear();
        // then
        assertThatThrownBy(() -> clientMemberService.removeAccount( findMember.getId()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("존재하지 않는 회원정보입니다.");
    }

    @Test
    @DisplayName("기관 가입 요청")
    void requestJoinOfInstitution() {
        //given
        List<Member> list = memberRepository.findAll();
        List<Institution> institutions = institutionRepository.findAll();
        List<JoinInfo> joinInfos = joinInfoRepository.findAll();
        Long joinInfoId = clientMemberService.requestJoinOfInstitution(list.get(0).getId(), institutions.get(1).getId());
        entityManager.flush();
        entityManager.clear();
        // when
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
    @DisplayName("회원탈퇴한 회원정보로 가입 요청")
    void requestJoinOfInstitution5() {
        // given
        List<Institution> institutions = institutionRepository.findAll();

        List<Member> list = memberRepository.findAll();
        Member findMember = list.get(0);
        findMember.deleted();
        entityManager.flush();
        entityManager.clear();

        // when
        assertThatThrownBy(() -> clientMemberService.requestJoinOfInstitution(findMember.getId(), institutions.get(0).getId()))
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
    @DisplayName("기관탈퇴한 기관정보로 기관 가입 요청")
    void requestJoinOfInstitution6() {
        // given
        List<Member> list = memberRepository.findAll();

        List<Institution> institutions = institutionRepository.findAll();
        Institution institution = institutions.get(0);
        institution.deleted();
        entityManager.flush();
        entityManager.clear();
        // when
        assertThatThrownBy(() -> clientMemberService.requestJoinOfInstitution(list.get(0).getId(), institution.getId()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("기관정보가 존재하지 않습니다.");
    }

    @Test
    @DisplayName("중복 기관 가입 요청")
    void requestJoinOfInstitution4() {
        //given
        List<JoinInfo> list = joinInfoRepository.findAll();
        entityManager.flush();
        entityManager.clear();

        // when
        // then
        assertThatThrownBy(() -> clientMemberService.requestJoinOfInstitution(list.get(0).getMember().getId(), list.get(0).getInstitution().getId()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("이미 존재하는 가입 요청입니다.");
    }

    @Test
    @DisplayName("기관 가입 요청 취소")
    void cancelRequestedJoinOfInstitution() {
        // given
        List<JoinInfo> list = joinInfoRepository.findAll();
        // when
        JoinInfo joinInfo = list.get(0);
        clientMemberService.cancelRequestedJoinOfInstitution(joinInfo.getId());
        entityManager.flush();
        entityManager.clear();
        // then
        assertThat(joinInfo.isDeleted()).isEqualTo(true);
    }

    @Test
    @DisplayName("존재하지 않는 가입 요청 취소")
    void cancelRequestedJoinOfInstitutionByNonMemberId() {
        // then
        assertThatThrownBy(() -> clientMemberService.cancelRequestedJoinOfInstitution( -1L))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("존재하지 않는 가입정보입니다.");
    }

    public JoinMemberDto getJoinMemberDtoByTestData(){
        JoinMemberDto joinMemberDto = new JoinMemberDto();
        joinMemberDto.setName("노성규");
        joinMemberDto.setEmail("shtjdrb");
        joinMemberDto.setPassword("shtjdrb123");
        joinMemberDto.setBirthDay("19950914");
        return joinMemberDto;
    }

    public AddressDto getAddressDtoByTestData(){
        AddressDto addressDto = new AddressDto();
        addressDto.setSggNm("서울시 마포구0");
        return addressDto;
    }

    public FileInfo getTestFile(){
        return FileInfo.createFile("노성규", "/photo", null);
    }

    private MyPageDto getMyPageDto() {
        MyPageDto myPageDto = new MyPageDto();
        myPageDto.setEmail("gkdlshtjdrb@naver.com");
        myPageDto.setName("박현우");
        myPageDto.setTelNo("01012341234");
        myPageDto.setBirthDay("19950101");
        return myPageDto;
    }
}