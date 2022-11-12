package com.metacrew.pr2s.dto;

import com.metacrew.pr2s.entity.enums.Usage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * RoomUsage 정보를 담고 있는 DTO
 * @author hyeonwoo
 * @since 2022.10.02
 */

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RoomUsageDto {
    private Usage usage;
    private String name;
    private String value;

    public RoomUsageDto(Usage usage){
        this.usage = usage;
        this.value = usage.getValue();
        this.name = usage.name();
    }
}
