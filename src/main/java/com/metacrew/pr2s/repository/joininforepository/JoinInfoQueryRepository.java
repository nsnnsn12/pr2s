package com.metacrew.pr2s.repository.joininforepository;

import com.metacrew.pr2s.dto.condition.JoinInfoConditionDto;
import com.metacrew.pr2s.entity.Institution;
import com.metacrew.pr2s.entity.JoinInfo;

import java.util.List;

public interface JoinInfoQueryRepository {
    List<JoinInfo> search(Institution institution, JoinInfoConditionDto joinInfoConditionDto);
}
