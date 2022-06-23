package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.entity.base.BaseEntity;
import com.metacrew.pr2s.entity.embedded.BoardAuthority;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Board 테이블과 매핑되는 엔티티이다.
 * 게시판을 수정 및 조회할 수 있다.
 * @author dongmin
 * @since 2022.06.23
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Board extends BaseEntity {
    @Id
    @Column(name = "board_id") @GeneratedValue
    private Long id;

    @JoinColumn(name = "institution_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Institution institution;

    @Column
    private String kindCode;

    @Column
    private boolean isPublic;

    @Embedded
    private BoardAuthority boardAuthority;
}
