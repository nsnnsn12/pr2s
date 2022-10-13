package com.metacrew.pr2s.validator;

import com.metacrew.pr2s.dto.condition.RoomSearchConditionDto;
import com.metacrew.pr2s.repository.memberrepository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * 방 조회 검색 조건의 유효성 검사를 위한 클래스
 * @author sunggyu
 * @since 2022.10.10
 */
@RequiredArgsConstructor
@Component
public class SearchConditionValidator extends AbstractValidator<RoomSearchConditionDto> {
    @Override
    protected void doValidate(RoomSearchConditionDto dto, Errors errors) {
        if(dto.getStartDate().isAfter(dto.getEndDate())){
            errors.rejectValue("datetime", "데이터유효성오류", "기간이 올바르지 않습니다.");
        }

        if(dto.getStartDate().isEqual(dto.getEndDate())){
            errors.rejectValue("datetime", "데이터유효성오류", "기간이 올바르지 않습니다.");
        }
    }
}
