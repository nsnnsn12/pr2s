package com.metacrew.pr2s.repository.memberrepository;

import com.metacrew.pr2s.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * JPQL 학습을 위한 테스트 Repository 클래스이다.
 * @author sunggyu
 * @since 2022.06.29
 */
@Repository
@RequiredArgsConstructor
public class MemberTestRepository {
    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public List<Member> findByLoginId(String loginId){
        return em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
                .setParameter("loginId", loginId)
                .getResultList();
    }

    public Member getMember(Long id) {
        Member member = em.find(Member.class, id);
        return member;
    }
}
