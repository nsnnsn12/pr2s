package com.metacrew.pr2s.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Room {
    @Id
    @Column(name = "room_id") @GeneratedValue
    private Long id;

    @Column
    private String title;
    
    //// TODO: 2022-06-16 File 매핑작업 필요 
}
