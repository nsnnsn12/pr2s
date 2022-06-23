package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.entity.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
/**
 * Address 테이블과 매핑되는 엔티티이다.
 * 주소를 수정 및 조회할 수 있다.
 * @author dongmin
 * @since 2022.06.23
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Address extends BaseEntity {
    @Id
    @Column(name = "address_id") @GeneratedValue
    private Long id;

    @Column
    private String roadFullAddr;

    @Column
    private String roadAddrPart1;

    @Column
    private String roadAddrPart2;

    @Column
    private String jibunAddr;

    @Column
    private String engAddr;

    @Column
    private String zipNo;

    @Column
    private String addrDetail;

    @Column
    private String admCd;

    @Column
    private String rnMgtSn;

    @Column
    private String bdMgtSn;

    @Column
    private String detBdNmList;

    @Column
    private String bdNm;

    @Column
    private String bdKdcd;

    @Column
    private String siNm;

    @Column
    private String sggNm;

    @Column
    private String emdNm;

    @Column
    private String liNm;

    @Column
    private String rn;

    @Column
    private String udrtYn;

    @Column
    private String buldMnnm;

    @Column
    private String buldSlno;

    @Column
    private String mtYn;

    @Column
    private String lnbrMnnm;

    @Column
    private String lnbrSlno;

    @Column
    private String emdNo;

    @Column
    private String entX;

    @Column
    private String entY;
}
