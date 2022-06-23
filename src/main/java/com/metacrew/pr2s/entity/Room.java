package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.entity.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @Column(name = "room_id") @GeneratedValue
    private Long id;

    @JoinColumn(name = "address_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Address address;

    @JoinColumn(name = "institution_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Institution institution;

    @JoinColumn(name = "file_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private File file;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private int maximumPersonCount;

}
