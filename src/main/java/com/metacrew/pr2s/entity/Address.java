package com.metacrew.pr2s.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Address 테이블과 매핑되는 엔티티이다.
 * 주소 정보를 가지고 있다.
 * @author sunggyu
 * @since 2022.06.12
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Address {
    @Id
    @Column(name = "address_id") @GeneratedValue
    private Long id;
}
