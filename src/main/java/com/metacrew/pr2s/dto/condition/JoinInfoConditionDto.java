package com.metacrew.pr2s.dto.condition;

import lombok.Getter;
import lombok.Setter;

/**
 * JoinInfo 조회시 사용되는 DTO.
 * @author sunggyu
 * @since 2022.07.22
 */
@Getter
@Setter
public class JoinInfoConditionDto {
    private Long institutionId;
    private Boolean isApproved;
}
