package com.metacrew.pr2s.dto;

import com.metacrew.pr2s.entity.Address;
import com.metacrew.pr2s.entity.File;
import com.metacrew.pr2s.entity.Institution;
import com.metacrew.pr2s.entity.Workdays;
import com.metacrew.pr2s.entity.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Workdays 정보를 담고 있는 DTO
 * @author hyeonwoo
 * @since 2022.07.07
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class WorkdaysDto {
    private Long id;
    private Boolean isMonday;
    private Boolean isTuesday;
    private Boolean isWednesday;
    private Boolean isThursday;
    private Boolean isFriday;
    private Boolean isSaturday;
    private Boolean isSunday;

    /**
     * Workdays ->  WorkdaysDto
     * @author hyeonwoo
     * @since 2022.07.07
     * @param workdays 기관정보
     */
    public WorkdaysDto(Workdays workdays) {
        this.id = workdays.getId();
        this.isMonday = workdays.getIsMonday();
        this.isTuesday = workdays.getIsTuesday();
        this.isWednesday = workdays.getIsWednesday();
        this.isThursday = workdays.getIsThursday();
        this.isFriday = workdays.getIsFriday();
        this.isSaturday = workdays.getIsSaturday();
        this.isSunday = workdays.getIsSunday();
    }
}
