package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.entity.base.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Bookmark 테이블과 매핑되는 엔티티이다.
 * @author hyeonwoo
 * @since 2022.06.23
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Bookmark extends BaseTimeEntity {
    @Id
    @Column(name = "bookmark_id") @GeneratedValue
    private Long id;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "room_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

    @JoinColumn(name = "institution_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Institution institution;

    @Column
    private Boolean isInstitution;
}
