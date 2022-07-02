package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.dto.AddressDto;
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

    /**
     * AddressDto -> Address
     * @author sunggyu
     * @since 2022.07.02
     * @param addressDto 엔티티로 변환할 값
     * @return Address 엔티티로 변환한 값
     */
    public static Address createAddressByAddressDto(AddressDto addressDto){
        Address address = new Address();
        address.roadFullAddr = addressDto.getRoadFullAddr();
        address.roadAddrPart1 = addressDto.getRoadAddrPart1();
        address.roadAddrPart2 = addressDto.getRoadAddrPart2();
        address.jibunAddr = addressDto.getJibunAddr();
        address.engAddr = addressDto.getEngAddr();
        address.zipNo = addressDto.getZipNo();
        address.addrDetail = addressDto.getAddrDetail();
        address.admCd = addressDto.getAdmCd();
        address.rnMgtSn = addressDto.getRnMgtSn();
        address.bdMgtSn = addressDto.getBdMgtSn();
        address.detBdNmList = addressDto.getDetBdNmList();
        address.bdNm = addressDto.getBdNm();
        address.bdKdcd = addressDto.getBdKdcd();
        address.siNm = addressDto.getSiNm();
        address.sggNm = addressDto.getSggNm();
        address.emdNm = addressDto.getEmdNm();
        address.liNm = addressDto.getLiNm();
        address.rn = addressDto.getRn();
        address.udrtYn = addressDto.getUdrtYn();
        address.buldMnnm = addressDto.getBuldMnnm();
        address.buldSlno = addressDto.getBuldSlno();
        address.mtYn = addressDto.getMtYn();
        address.lnbrMnnm = addressDto.getLnbrMnnm();
        address.lnbrSlno = addressDto.getLnbrSlno();
        address.emdNo = addressDto.getEmdNo();
        address.entX = addressDto.getEntX();
        address.entY = addressDto.getEntY();

        return address;
    }
}
