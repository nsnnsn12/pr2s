package com.metacrew.pr2s.repository.institutionrepository;

import com.metacrew.pr2s.dto.InstitutionDto;
import com.metacrew.pr2s.entity.Institution;
import com.metacrew.pr2s.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * JPQL 학습을 위한 테스트 Repository 클래스이다.
 * @author hyeonwoo
 * @since 2022.07.01
 */
@Repository
@RequiredArgsConstructor
public class InstitutionTestRepository {
    private final EntityManager em;

    public Long save(Institution givenInstitution){
        em.persist(givenInstitution);
        return givenInstitution.getId();
    }

    public Institution getInstitution(Long id) {
        return em.find(Institution.class, id);
    }
}
