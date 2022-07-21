package com.metacrew.pr2s.repository.roomrepository;

import com.metacrew.pr2s.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Room 테이블과 매핑되는 레퍼지토리이다.
 * @author nahyun
 * @since 2022.07.16
 */

public interface RoomRepository extends JpaRepository<Room, Long> {

    //id로 리스트조회
   @Query("select r from Room r where r.id = :roomId")
   List<Room> findByRoomListLongId(Long roomId);

   //title로 리스트조회
    @Query("select r from Room r where r.title LIKE CONCAT('%',:title,'%')")
    List<Room> findByRoomTitle(String title);

    //id로 단건 조회
    @Query("select r from Room r where r.id = :roomId")
    Room findByRoomId(Long roomId);

    //전체 조회
    @Query("select r from Room r")
    List<Room> findAllRoom();

    //가능인원수로 조회
    @Query("select r from Room r where r.maximumPersonCount <= :maximumPersonCount")
    List<Room> findByRoomLessThanMaxPerCnt(int maximumPersonCount);


}
