package com.metacrew.pr2s.repository.roomrepository;

import com.metacrew.pr2s.dto.RoomDto;
import com.metacrew.pr2s.entity.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RoomTestRepository {

/**
 * JPQL 학습을 위한 테스트 Repository 클래스이다.
 * @author nahyun
 * @since 2022.07.01
 */
    private final EntityManager em;

    //등록
    public Room register(Room newRoom){
        em.persist(newRoom);
        return newRoom;
    }

    //타이틀로 리스트 조회
    public List<Room> findByRoomTitle(String title){
        return em.createQuery("select r from Room r where r.title LIKE CONCAT('%',:title,'%')",Room.class)
                .setParameter("title",title)
                .getResultList();
    }

    //id로 단건조회
    public Room findByRoomLongId(Long roomId){
        return em.find(Room.class,roomId);
    }

    //모두 조회
    public List<Room> findAllRoom(){
        return em.createQuery("select r from Room r", Room.class)
                .getResultList();
    }




}
