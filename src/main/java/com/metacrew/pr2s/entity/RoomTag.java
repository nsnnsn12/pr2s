package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.dto.RoomTagDto;
import com.metacrew.pr2s.dto.RoomUsageDto;
import com.metacrew.pr2s.entity.enums.Usage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * RoomTag 테이블과 매핑되는 엔티티이다.
 * 방의 태그를 수정 및 조회할 수 있다.
 * @author hyeonwoo
 * @since 2022.10.11
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RoomTag {
    @Id
    @Column(name = "room_tag_id") @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Column
    private String name;

    /**
     * RoomUsageDto -> RoomUsage
     * @author hyeonwoo
     * @since 2022.10.02
     * @param roomTagDto 방 태그 정보
     * @param room 방 엔터티
     * @return RoomTag 엔티티로 변환한 값
     */
    public static RoomTag createRoomTag(RoomTagDto roomTagDto, Room room){
        RoomTag roomTag = new RoomTag();
        roomTag.name = roomTagDto.getName();
        roomTag.room = room;

        return roomTag;
    }

}
