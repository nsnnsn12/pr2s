package com.metacrew.pr2s.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * RoomImage 테이블과 매핑되는 엔티티이다.
 * @author nahyun
 * @since 2022.06.23
 */

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RoomImage {
    @Id
    @Column(name = "room_image_id") @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    // TODO: 2022-06-23 File 엔티티 객체 매핑 필요

    public static RoomImage createRoomImage(Room room, FileInfo fileInfo){
        RoomImage roomImage = new RoomImage();
        roomImage.id= fileInfo.getId();
        roomImage.room = room;
        //파일

        return roomImage;
    }

}
