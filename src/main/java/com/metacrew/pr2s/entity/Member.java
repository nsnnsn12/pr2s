package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.entity.base.BaseEntity;
import com.metacrew.pr2s.entity.enums.FileType;
import com.metacrew.pr2s.entity.enums.Gender;
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
public class Member extends BaseEntity {
    @Id
    @Column(name = "member_id") @GeneratedValue
    private Long id;

    @JoinColumn(name = "address_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Address address;

    @JoinColumn(name = "file_id")
    @OneToOne(fetch = FetchType.LAZY)
    private File imageFile;

    @Column
    private String email;
    @Column
    private String telNo;

    @Column
    private String loginId;
    @Column
    private String password;
    @Column
    private String name;

    @Column
    private String birthDay;

    @Enumerated(EnumType.STRING)
    @Column
    private Gender gender;
}
