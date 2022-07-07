package com.metacrew.pr2s.repository.workdaysrepository;

import com.metacrew.pr2s.entity.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Workdays 테이블과 매핑되는 레퍼지토리이다.
 * @author hyeonwoo
 * @since 2022.07.07
 */
public interface WorkdaysRepository extends JpaRepository<Institution, Long>{
}
