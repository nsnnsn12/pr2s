package com.metacrew.pr2s.service.memberservice;

import com.metacrew.pr2s.dto.JoinMemberDto;
import com.metacrew.pr2s.dto.MyPageDto;
import com.metacrew.pr2s.entity.Member;
import com.metacrew.pr2s.repository.memberrepository.MemberTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * JPQL 학습을 위한 테스트 서비스 클래스이다.
 * @author sunggyu
 * @since 2022.06.29
 */
@Service
@Transactional
@RequiredArgsConstructor
public class MemberTestService implements MemberService{
    private final MemberTestRepository memberTestRepository;
    // TODO: 2022-06-25 회원탈퇴 메소드
    // TODO: 2022-06-25 기관에 회원 가입 요청
    /**
     * 회원가입
     * Member 엔티티를 입력받아 loginId 중복을 확인하고
     * 중복된 경우는 예외를 던지고
     * 중복이 아닌 경우는 엔티티를 DB에 저장하고 key값을 리턴한다.
     * @author sunggyu
     * @since 2022.06.29
     * @param member 등록할 회원 정보.
     * @return 등록한 회원 key 값.
     */
    /*public Long join(JoinMemberDto joinMember){
        Member member = new Member();
        member.joinMemberDto(joinMember);
        validateDuplicateMember(member.getLoginId());
        memberTestRepository.save(member);
        return member.getId();
    }*/

    /**
     * 입력받은 문자열이 Member 테이블의 loginId 컬럼과 중복된다면 예외를 던진다.
     * @author sunggyu
     * @since 2022.06.29
     */
    private void validateDuplicateMember(String loginId){
        List<Member> result = memberTestRepository.findByLoginId(loginId);
        if(result.size() > 0) throw new IllegalStateException("이미 존재하는 회원입니다.");
    }

    /**
     * 마이페이지 정보 조회
     * 입력받은 id로 MyPage 정보를 조회하여 리턴한다.
     * @author sunggyu
     * @since 2022.07.01
     * @param id 조회할 id값
     * @return MyPageDto 사용자에 보여 줄 마이페이지 정보
     */
    public MyPageDto getMyPageInfo(Long id) {
        Member findMember = memberTestRepository.getMember(id);
        return new MyPageDto(findMember);
    }

    /**
     * 마이페이지 정보 수정
     * 수정할 내용과 수정할 entity key를 입력받아
     * 마이 페이지에 해당하는 회원정보를 수정 후
     * entity key를 리턴한다.
     * @author sunggyu
     * @since 2022.07.01
     * @param id 수정할 entity key
     * @param myPageDto 수정할 내용을 담고 있는 dto
     * @return 수정한 회원 key 값.
     */
    public Long updateForMyPage(MyPageDto myPageDto, Long id){
        Member member = memberTestRepository.getMember(id);
        member.updateForMyPage(myPageDto);
        return member.getId();
    }

    /**
     * 회원탈퇴
     * 회원탈퇴할 entity key를 입력받아
     * 회원탈퇴 처리한다.
     * @author sunggyu
     * @since 2022.07.01
     * @param id 회원탈퇴할 entity key
     */
    public void removeAccount(Long id){
        Member findMember = memberTestRepository.getMember(id);
        if(!findMember.getIsDeleted()) findMember.deleted();
    }
}
