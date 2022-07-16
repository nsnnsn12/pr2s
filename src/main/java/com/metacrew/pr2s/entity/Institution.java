package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.dto.InstitutionDto;
import com.metacrew.pr2s.entity.base.BaseEntity;
import com.metacrew.pr2s.entity.embedded.Period;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

/**
 * Institution 테이블과 매핑되는 엔티티이다.
 * 기관 정보를 수정 및 조회할 수 있다.
 * @author nahyun
 * @since 2022.06.16
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Institution extends BaseEntity {
    @Id
    @Column(name = "institution_id") @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private boolean isApprovedRegistration;

    @Column
    private String telNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workdays_id")
    private Workdays workdays;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private File thumbnail;

    @Embedded
    private Period period;

    /**
     * InstitutionDto -> Institution
     * @author hyeonwoo
     * @since 2022.07.07
     * @param institutionDto 엔티티로 변환할 값
     * @param workdays Workdays 엔티티
     * @param address Address 엔티티
     * @return Institution 엔티티로 변환한 값
     */
    public static Institution createInstitution(InstitutionDto institutionDto, Workdays workdays, Address address){
        Institution institution = new Institution();
        institution.name = institutionDto.getName();
        institution.telNumber = institutionDto.getTelNumber();
        institution.period = institutionDto.getPeriod();
        institution.workdays = workdays;
        institution.address = address;
        /// TODO: 2022-07-16 썸네일 입력 필요
        return institution;
    }

    /**
     * InstitutionDto 내용으로 Entity 변경
     * @author hyeonwoo
     * @since 2022.07.07
     * @param institutionDto 변경할 기관 기본 정보
     * @param workdays 변경할 기관 운영 요일 정보
     * @param address 변경할 기관 주소 정보
     */
    public void updateInstitution(InstitutionDto institutionDto, Workdays workdays, Address address) {
        this.name = institutionDto.getName();
        this.telNumber = institutionDto.getTelNumber();
        this.period = institutionDto.getPeriod();
        this.workdays = workdays;
        this.address = address;
        /// TODO: 2022-07-16 썸네일 수정 필요
    }

}
