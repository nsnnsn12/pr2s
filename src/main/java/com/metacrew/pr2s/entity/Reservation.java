package com.metacrew.pr2s.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {
    @Id
    @Column(name = "reservation_id") @GeneratedValue
    private Long id;
}
