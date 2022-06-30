package com.metacrew.pr2s.repository;

import com.metacrew.pr2s.entity.JoinInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JoinInfo 테이블과 매핑되는 레퍼지토리이다.
 * @author hyeonwoo
 * @since 2022.06.26
 */
public interface JoinInfoRepository extends JpaRepository<JoinInfo, Long>{
}
