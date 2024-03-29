package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.dto.InstitutionCreateDto;
import com.metacrew.pr2s.entity.base.BaseEntity;
import com.metacrew.pr2s.entity.embedded.TimePeriod;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workdays_id")
    private Workdays workdays;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_info_id")
    private FileInfo fileInfo;

    @Embedded
    private TimePeriod timePeriod;

    /**
     * InstitutionDto -> Institution
     * @author hyeonwoo
     * @since 2022.07.07
     * @param institutionCreateDto 엔티티로 변환할 값
     * @param workdays Workdays 엔티티
     * @return Institution 엔티티로 변환한 값
     */
    public static Institution createInstitution(InstitutionCreateDto institutionCreateDto, Workdays workdays){
        Institution institution = new Institution();
        institution.name = institutionCreateDto.getName();
        institution.telNumber = institutionCreateDto.getTelNumber();
        institution.timePeriod = institutionCreateDto.getTimePeriod();
        institution.workdays = workdays;
        /// TODO: 2022-07-16 썸네일 입력 필요
        return institution;
    }

    /**
     * InstitutionDto 내용으로 Entity 변경
     * @author hyeonwoo
     * @since 2022.07.07
     * @param institutionCreateDto 변경할 기관 기본 정보
     */
    public void updateInstitution(InstitutionCreateDto institutionCreateDto) {
        this.name = institutionCreateDto.getName();
        this.telNumber = institutionCreateDto.getTelNumber();
        this.timePeriod = institutionCreateDto.getTimePeriod();
        /// TODO: 2022-07-16 썸네일 수정 필요
    }

}
