package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.dto.WorkdaysDto;
import com.metacrew.pr2s.entity.base.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Workdays 테이블과 매핑되는 엔티티이다.
 * 기관의 운영 요일을 수정 및 조회할 수 있다.
 * @author hyeonwoo
 * @since 2022.07.07
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Workdays extends BaseTimeEntity {
    @Id
    @Column(name = "workdays_id") @GeneratedValue
    Long id;

    @Column
    private Boolean isMonday;

    @Column
    private Boolean isTuesday;

    @Column
    private Boolean isWednesday;

    @Column
    private Boolean isThursday;

    @Column
    private Boolean isFriday;

    @Column
    private Boolean isSaturday;

    @Column
    private Boolean isSunday;

    /**
     * WorkdaysDto -> Workdays
     * @author hyeonwoo
     * @since 2022.07.07
     * @param workdaysDto 엔티티로 변환할 값
     * @return Workdays 엔티티로 변환한 값
     */
    public static Workdays createWorkdays(WorkdaysDto workdaysDto){
        Workdays workdays = new Workdays();
        workdays.isMonday = workdaysDto.getIsMonday();
        workdays.isTuesday = workdaysDto.getIsTuesday();
        workdays.isWednesday = workdaysDto.getIsWednesday();
        workdays.isThursday = workdaysDto.getIsThursday();
        workdays.isFriday = workdaysDto.getIsFriday();
        workdays.isSaturday = workdaysDto.getIsSaturday();
        workdays.isSunday = workdaysDto.getIsSunday();
        return workdays;
    }
}
