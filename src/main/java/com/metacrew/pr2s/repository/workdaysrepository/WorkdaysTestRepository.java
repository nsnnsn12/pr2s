package com.metacrew.pr2s.repository.workdaysrepository;

import com.metacrew.pr2s.dto.WorkdaysDto;
import com.metacrew.pr2s.entity.Institution;
import com.metacrew.pr2s.entity.Workdays;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

/**
 * JPQL 학습을 위한 테스트 Repository 클래스이다.
 * @author hyeonwoo
 * @since 2022.07.07
 */
@Repository
@RequiredArgsConstructor
public class WorkdaysTestRepository {
    private final EntityManager em;

    public Workdays save(Workdays givenWorkdays){
        em.persist(givenWorkdays);
        return givenWorkdays;
    }

    public Optional<Workdays> getWorkdays(Long id) {
        return Optional.ofNullable(em.find(Workdays.class, id));
    }
}
