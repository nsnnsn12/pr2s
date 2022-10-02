package com.metacrew.pr2s.repository.institutionrepository;

import com.metacrew.pr2s.entity.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Institution 테이블과 매핑되는 레퍼지토리이다.
 * @author hyeonwoo
 * @since 2022.06.26
 */
public interface InstitutionRepository extends JpaRepository<Institution, Long>{
}
