package com.metacrew.pr2s.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
}
