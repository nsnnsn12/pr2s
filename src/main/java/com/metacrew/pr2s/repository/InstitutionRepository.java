package com.metacrew.pr2s.repository;

import com.metacrew.pr2s.entity.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Institution 테이블과 매핑되는 레퍼지토리이다.
 * 기관을 수정 및 조회할 수 있다.
 * @author hyeonwoo
 * @since 2022.06.26
 */
public interface InstitutionRepository extends JpaRepository<Institution, Long>{
}
