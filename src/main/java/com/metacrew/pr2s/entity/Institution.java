package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.dto.InstitutionDto;
import com.metacrew.pr2s.entity.base.BaseEntity;
import com.metacrew.pr2s.entity.embedded.Period;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Boolean isApprovedRegistration;

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
    private File file;

    @Embedded
    private Period period;

    /**
     * InstitutionDto -> Institution
     * @author hyeonwoo
     * @since 2022.07.07
     * @param institutionDto 엔티티로 변환할 값
     * @param workdays Workdays 엔티티
     * @return Institution 엔티티로 변환한 값
     */
    public static Institution createInstitution(InstitutionDto institutionDto, Workdays workdays){
        Institution institution = new Institution();
        institution.name = institutionDto.getName();
        institution.telNumber = institutionDto.getTelNumber();
        institution.workdays = workdays;
        return institution;
    }

    /**
     * InstitutionDto 내용으로 Entity 변경
     * @author hyeonwoo
     * @since 2022.07.07
     * @param institutionDto 엔티티로 변환할 값
     * @return void 리턴하지 않음
     */
    public void setForUpdate(InstitutionDto institutionDto) {
        this.name = institutionDto.getName();
        this.telNumber = institutionDto.getTelNumber();
    }

}
