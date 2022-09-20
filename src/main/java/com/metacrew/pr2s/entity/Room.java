package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.dto.AddressDto;
import com.metacrew.pr2s.dto.RoomDto;
import com.metacrew.pr2s.entity.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Room 테이블과 매핑되는 엔티티이다.
 * @author hyeonwoo
 * @since 2022.06.23
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Room extends BaseEntity {
    @Id
    @Column(name = "room_id")
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_address_id")
    private InstitutionAddress institutionAddress;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private int maximumPersonCount;

    @Column
    private int floor;

    @Column
    private String usage;

    @Column
    private boolean isUdrt;

    /**
     * RoomDto -> Room
     * @author nahyun
     * @since 2022.07.07
     * @param roomDto 엔티티로 변환할 값
     * @return Room 엔티티로 변환한 값
     */
    public static Room createRoomByRoomDto(RoomDto roomDto, InstitutionAddress institutionAddress){
        Room room = new Room();
        room.id = roomDto.getId();
        room.title = roomDto.getTitle();
        room.description = roomDto.getDescription();
        room.maximumPersonCount = roomDto.getMaximumPersonCount();
        room.institutionAddress = institutionAddress;

        return room;
    }

    //수정시사용
    public void setForUpdateRoom(RoomDto roomDto) {
        title= roomDto.getTitle();
        description= roomDto.getDescription();
        maximumPersonCount= roomDto.getMaximumPersonCount();
    }

    //삭제시사용
    public void setForDeleteRoom(RoomDto roomDto){
        deleted();
    }

}

