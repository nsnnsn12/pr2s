package com.metacrew.pr2s.repository.joinforepository;

import com.metacrew.pr2s.dto.condition.JoinInfoConditionDto;
import com.metacrew.pr2s.entity.JoinInfo;

import java.util.List;

public interface JoinInfoQueryRepository {
    List<JoinInfo> search(JoinInfoConditionDto joinInfoConditionDto);
}
