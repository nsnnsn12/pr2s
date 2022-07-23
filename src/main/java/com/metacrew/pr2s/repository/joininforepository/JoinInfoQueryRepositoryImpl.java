package com.metacrew.pr2s.repository.joininforepository;

import com.metacrew.pr2s.dto.condition.JoinInfoConditionDto;
import com.metacrew.pr2s.entity.Institution;
import com.metacrew.pr2s.entity.JoinInfo;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.metacrew.pr2s.entity.QJoinInfo.*;

public class JoinInfoQueryRepositoryImpl implements JoinInfoQueryRepository {
    private final JPAQueryFactory queryFactory;
    public JoinInfoQueryRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }
    @Override
    public List<JoinInfo> search(Institution institution, JoinInfoConditionDto joinInfoConditionDto) {
        //엔티티 값을 어떻게 받을 것인가?
        // TODO: 2022-07-23 지금은 승인여부의 대한 조건밖에 없지만 필요 조건 추가
        return queryFactory
                .select(joinInfo)
                .from(joinInfo)
                .where(institutionEq(institution)
                        ,approvedEq(joinInfoConditionDto.getIsApproved()))
                .fetch();
    }

    private BooleanExpression approvedEq(Boolean isApproved){
        return isApproved != null ? joinInfo.isApproved.eq(isApproved) : null;
    }

    private BooleanExpression institutionEq(Institution institution){
        return institution != null ? joinInfo.institution.eq(institution) : null;
    }
}
