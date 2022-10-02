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

/**
 * JPQL 학습을 위한 테스트 Repository 클래스이다.
 * @author nahyun
 * @since 2022.07.01
 */
@Repository
@RequiredArgsConstructor
public class RoomRepositoryImpl {
    private final EntityManager em;

    //타이틀로 리스트 조회
    public List<Room> findByRoomTitle(String title){
        return em.createQuery("select r from Room r where r.title LIKE CONCAT('%',:title,'%')",Room.class)
                .setParameter("title",title)
                .getResultList();
    }

}
