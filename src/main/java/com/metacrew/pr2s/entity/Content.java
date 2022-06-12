package com.metacrew.pr2s.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Content 테이블과 매핑되는 엔티티이다.
 * 게시글 정보를 수정 및 조회할 수 있다.
 * @author sunggyu
 * @since 2022.06.12
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Content {
    @Id
    @Column(name = "content_id") @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;
}
