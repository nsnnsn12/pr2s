package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.entity.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Reservation 테이블과 매핑되는 엔티티이다.
 * @author hyeonwoo
 * @since 2022.06.23
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Reservation extends BaseEntity {
    @Id
    @Column(name = "reservation_id") @GeneratedValue
    private Long id;

    @JoinColumn(name = "room_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    // TODO: 2022-06-23 period 객체 정의 필요

    @Column
    private Boolean isReserveApproved;

    @Column
    private LocalDateTime reserveApproveDate;

    @Column
    private int peopleCount;

    @Column
    private String usingMemberName;

    @Column
    private String usingMemberTelNumber;
}
