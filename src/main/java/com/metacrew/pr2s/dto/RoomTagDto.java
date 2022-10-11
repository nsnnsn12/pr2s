package com.metacrew.pr2s.dto;

import com.metacrew.pr2s.entity.enums.Usage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * RoomTag 정보를 담고 있는 DTO
 * @author hyeonwoo
 * @since 2022.10.11
 */

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RoomTagDto {
    private String name;
}
