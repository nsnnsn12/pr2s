package com.metacrew.pr2s.repository.roomtagrepository;

import com.metacrew.pr2s.entity.RoomTag;
import com.metacrew.pr2s.entity.RoomUsage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * RoomTag 테이블과 매핑되는 레퍼지토리이다.
 * @author hyeonwoo
 * @since 2022.10.11
 */
public interface RoomTagRepository extends JpaRepository<RoomTag, Long>{
}