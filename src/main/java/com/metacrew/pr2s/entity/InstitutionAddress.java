package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.dto.InstitutionCreateDto;
import com.metacrew.pr2s.entity.base.BaseEntity;
import com.metacrew.pr2s.entity.embedded.TimePeriod;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Institution_Address 테이블과 매핑되는 엔티티이다.
 * 기관의 주소정보를 수정 및 조회할 수 있다.
 * @author hyeonwoo
 * @since 2022.09.18
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class InstitutionAddress extends BaseEntity {
    @Id
    @Column(name = "institution_address_id") @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_id")
    private Institution institution;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    /**
     * InstitutionDto -> Institution
     * @author hyeonwoo
     * @since 2022.07.07
     * @param institution 기관 엔터티
     * @param address Workdays 엔티티
     * @return Institution 엔티티로 변환한 값
     */
    public static InstitutionAddress createInstitutionAddress(Institution institution, Address address){
        InstitutionAddress institutionAddress = new InstitutionAddress();
        institutionAddress.institution = institution;
        institutionAddress.address = address;
        return institutionAddress;
    }

}
