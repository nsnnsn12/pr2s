package com.metacrew.pr2s.dto;

import com.metacrew.pr2s.entity.embedded.TimePeriod;
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
    private TimePeriod timePeriod;
}
