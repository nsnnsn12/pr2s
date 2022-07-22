package com.metacrew.pr2s.dto;

import com.metacrew.pr2s.entity.Address;
import com.metacrew.pr2s.entity.File;
import com.metacrew.pr2s.entity.Institution;
import com.metacrew.pr2s.entity.Workdays;
import com.metacrew.pr2s.entity.base.BaseEntity;
import com.metacrew.pr2s.entity.embedded.Period;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Institution 정보를 담고 있는 DTO
 * @author hyeonwoo
 * @since 2022.07.07
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class InstitutionCreateDto {
    private String name;
    private String telNumber;
    private Period period;

    /**
     * Institution ->  InstitutionDto
     * @author hyeonwoo
     * @since 2022.07.07
     * @param institution 기관정보
     */
    public InstitutionCreateDto(Institution institution) {
        this.name = institution.getName();
        this.telNumber = institution.getTelNumber();
        this.period = institution.getPeriod();
    }
}
