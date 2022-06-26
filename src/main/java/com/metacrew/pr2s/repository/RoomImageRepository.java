package com.metacrew.pr2s.repository;

import com.metacrew.pr2s.entity.RoomImage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * RoomImage 테이블과 매핑되는 레퍼지토리이다.
 * 방 사진을 수정 및 조회할 수 있다.
 * @author hyeonwoo
 * @since 2022.06.26
 */
public interface RoomImageRepository extends JpaRepository<RoomImage, Long>{
}
