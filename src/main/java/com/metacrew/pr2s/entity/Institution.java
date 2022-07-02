package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.dto.InstitutionDto;
import com.metacrew.pr2s.entity.base.BaseEntity;
import com.metacrew.pr2s.entity.embedded.Period;
import com.metacrew.pr2s.entity.enums.WorkDay;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column
    @Enumerated(EnumType.STRING)
    private WorkDay workday;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private File file;

    @Embedded
    private Period period;

    public void setForInsert(InstitutionDto institutionDto) {
        this.name = institutionDto.getName();
        this.telNumber = institutionDto.getTelNumber();
        this.workday = institutionDto.getWorkday();
    }

    public void setForUpdate(InstitutionDto institutionDto) {
        this.name = institutionDto.getName();
        this.workday = institutionDto.getWorkday();
    }

    public Institution(InstitutionDto dto) {
        setForInsert(dto);
    }

    public void deleteInstitution(){
        // TODO: 2022-07-01 BaseEntity 삭제 메소드 구현 후 시작
    }

}
