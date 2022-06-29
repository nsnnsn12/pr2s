package com.metacrew.pr2s.service.memberservice;

import com.metacrew.pr2s.entity.Member;
import com.metacrew.pr2s.repository.MemberTestRepository;
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

    /**
     * Member 엔티티를 입력받아 loginId 중복을 확인하고
     * 중복된 경우는 예외를 던지고
     * 중복이 아닌 경우는 엔티티를 DB에 저장하고 key값을 리턴한다.
     * @author sunggyu
     * @since 2022.06.29
     */
    public Long join(Member member){
        validateDuplicateMember(member.getLoginId());
        memberTestRepository.save(member);
        return member.getId();
    }

    /**
     * 입력받은 문자열이 Member 테이블의 loginId 컬럼과 중복된다면 예외를 던진다.
     * @author sunggyu
     * @since 2022.06.29
     */
    private void validateDuplicateMember(String loginId){
        List<Member> result = memberTestRepository.findByLoginId(loginId);
        if(result.size() > 0) throw new IllegalStateException("이미 존재하는 회원입니다.");
    }
}
