package com.metacrew.pr2s.dto;

import com.metacrew.pr2s.entity.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * Address Entity와 매핑되는 DTO.
 * @author sunggyu
 * @since 2022.07.02
 */
@Getter @Setter
public class AddressDto {
    @NotBlank(message = "도로명 주소는 필수 입력 값입니다.")
    private String roadFullAddr;

    private String roadAddrPart1;

    private String roadAddrPart2;

    private String jibunAddr;

    private String engAddr;

    @NotBlank(message = "우편번호는 필수 입력 값입니다.")
    private String zipNo;

    private String admCd;

    private String rnMgtSn;

    private String bdMgtSn;

    private String detBdNmList;

    private String bdNm;

    private String bdKdcd;

    private String siNm;

    private String sggNm;

    private String emdNm;

    private String liNm;

    private String rn;

    private String udrtYn;

    private String buldMnnm;

    private String buldSlno;

    private String mtYn;

    private String lnbrMnnm;

    private String lnbrSlno;

    private String emdNo;

    private String entX;

    private String entY;
}
