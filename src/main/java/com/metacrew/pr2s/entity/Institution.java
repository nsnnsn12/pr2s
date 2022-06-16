package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.entity.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Institution 테이블과 매핑되는 엔티티이다.
 * 기관 정보를 수정 및 조회할 수 있다.
 * @author nahyun
 * @since 2022.06.16
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Institution extends BaseEntity {
    @Id
    @Column(name = "institution_id") @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    //// TODO: 2022-06-16 File 엔티티 객체 매핑 필요

}
