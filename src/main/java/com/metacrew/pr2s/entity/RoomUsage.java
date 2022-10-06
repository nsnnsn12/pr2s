package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.dto.RoomUsageDto;
import com.metacrew.pr2s.entity.base.BaseEntity;
import com.metacrew.pr2s.entity.enums.Usage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * RoomUsage 테이블과 매핑되는 엔티티이다.
 * 방의 사용용도를 수정 및 조회할 수 있다.
 * @author hyeonwoo
 * @since 2022.10.02
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RoomUsage {
    @Id
    @Column(name = "room_usage_id") @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Column @Enumerated(EnumType.STRING)
    private Usage usage;

    /**
     * RoomUsageDto -> RoomUsage
     * @author hyeonwoo
     * @since 2022.10.02
     * @param room 방 엔터티
     * @param roomUsageDto 방 사용용도 정보
     * @return RoomUsage 엔티티로 변환한 값
     */
    public static RoomUsage createRoomUsage(RoomUsageDto roomUsageDto, Room room){
        RoomUsage roomUsage = new RoomUsage();
        roomUsage.room = room;
        roomUsage.usage = roomUsageDto.getUsage();

        return roomUsage;
    }

}
