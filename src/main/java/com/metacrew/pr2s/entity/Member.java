package com.metacrew.pr2s.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Member 테이블과 매핑되는 엔티티이다.
 * @author sunggyu
 * @since 2022.06.17
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {
    @Id
    @Column(name = "client_id") @GeneratedValue
    private Long id;
}
