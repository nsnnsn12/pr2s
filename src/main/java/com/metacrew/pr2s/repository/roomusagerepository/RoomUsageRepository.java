package com.metacrew.pr2s.repository.roomusagerepository;

import com.metacrew.pr2s.entity.RoomUsage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * RoomUsage 테이블과 매핑되는 레퍼지토리이다.
 * @author hyeonwoo
 * @since 2022.10.02
 */
public interface RoomUsageRepository extends JpaRepository<RoomUsage, Long>{
}