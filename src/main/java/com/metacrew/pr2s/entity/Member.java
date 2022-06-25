package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.entity.enums.FileType;
import com.sun.xml.bind.v2.TODO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @Column(name = "member_id") @GeneratedValue
    private Long id;

    @JoinColumn(name = "address_id")
    @OneToOne
    private Address address;
    @Column
    private String email;
    @Column
    private String telNo;
    @Column
    private String registId;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private boolean isDeleted;

    // TODO: 2022-06-19 gender 관련 enum 타입 필요
    @Enumerated(EnumType.STRING)
    @Column
    private FileType fileType;
}
