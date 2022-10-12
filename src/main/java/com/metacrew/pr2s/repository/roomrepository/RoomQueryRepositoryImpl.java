package com.metacrew.pr2s.repository.roomrepository;

import com.metacrew.pr2s.dto.RoomUsageDto;
import com.metacrew.pr2s.dto.condition.JoinInfoConditionDto;
import com.metacrew.pr2s.dto.condition.RoomSearchConditionDto;
import com.metacrew.pr2s.dto.room.SearchedRoomDto;
import com.metacrew.pr2s.entity.*;
import com.metacrew.pr2s.entity.enums.Usage;
import com.metacrew.pr2s.repository.joininforepository.JoinInfoQueryRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static com.metacrew.pr2s.entity.QAddress.*;
import static com.metacrew.pr2s.entity.QInstitutionAddress.institutionAddress;
import static com.metacrew.pr2s.entity.QReservation.reservation;
import static com.metacrew.pr2s.entity.QRoom.room;
import static com.metacrew.pr2s.entity.QRoomImage.roomImage;
import static com.metacrew.pr2s.entity.QRoomTag.roomTag;
import static com.metacrew.pr2s.entity.QRoomUsage.roomUsage;


public class RoomQueryRepositoryImpl implements RoomQueryRepository {
    private final JPAQueryFactory queryFactory;
    public RoomQueryRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }
    @Override
    public List<Room> search(RoomSearchConditionDto roomSearchConditionDto) {
        //리턴값을 dto로 받을 것인가? 일단 dto로 받고 해당 기능이 더 확장되면 다시 entity로 받는 것으로 해야 할까?
        // 왜? 일대다 조인이 많아질 수록 row 갯수는 곱해짐. room의 usage가 10개이고 tag가 10개라고 하면 벌써 row가 100이 되어버림.
        return queryFactory
                .select(room)
                .from(room)
                .join(room.institutionAddress, institutionAddress) //다대일
                .join(institutionAddress.address, address) //일대일
                .where(room.isDeleted.eq(false)
                        , likeTitle(roomSearchConditionDto.getTitle())
                        , goeMaximumPersonCount(roomSearchConditionDto.getMaximumPersonCount())
                        , eqSiNm(roomSearchConditionDto.getSiNm())
                        , eqSggNm(roomSearchConditionDto.getSggNm())
                        , eqEmdNm(roomSearchConditionDto.getEmdNm())
                        , notInDatePeriod(roomSearchConditionDto.getStartDate(), roomSearchConditionDto.getEndDate())
                        , inUsage(roomSearchConditionDto.getUsages())
                        , likeTag(roomSearchConditionDto.getTitle()))
                .fetch();
    }

    //제목 조건
    private BooleanExpression likeTitle(String title){
        return StringUtils.hasText(title) ? room.title.like(title) : null;
    }

    //인원수 조건
    private BooleanExpression goeMaximumPersonCount(Integer maximumPersonCount){
        return maximumPersonCount != null ? room.maximumPersonCount.goe(maximumPersonCount): null;
    }

    //시도 조건
    private BooleanExpression eqSiNm(String siNm){
        return StringUtils.hasText(siNm) ? address.siNm.eq(siNm): null;
    }

    //시군구 조건
    private BooleanExpression eqSggNm(String sggNm){
        return StringUtils.hasText(sggNm) ? address.sggNm.eq(sggNm): null;
    }

    //읍면동 조건
    private BooleanExpression eqEmdNm(String emdNm){
        return StringUtils.hasText(emdNm) ? address.emdNm.eq(emdNm): null;
    }

    //날짜조건
    private BooleanExpression notInDatePeriod(LocalDateTime startDate, LocalDateTime endDate){
        if(startDate != null && endDate != null){
            return room.id.notIn(
                    JPAExpressions
                        .select(reservation.room.id)
                        .from(reservation)
                        .where(reservation.isDeleted.eq(false)
                                , reservation.datePeriod.startDate.between(startDate, endDate)
                                        .or(reservation.datePeriod.endDate.between(startDate, endDate)))
                        .groupBy(reservation.room.id)
            );
        }
        return null;
    }

    //방 사용 용도
    //성능 향상을 위해 조건에 맞는 사용용도만 조회 후 방 id로 group by 하여 row 수를 줄인다.
    private BooleanExpression inUsage(List<Usage> usages){
        if(usages != null){
            return room.id.in(
                    JPAExpressions
                    .select(roomUsage.room.id)
                    .from(roomUsage)
                    .where(roomUsage.usage.in(usages))
                    .groupBy(roomUsage.room.id)
            );
        }
        return null;
    }

    //타이틀 명을 이용하여 태그 조건 조회
    //성능 향상을 위해 조건에 맞는 태그만 조회 후 방 id로 group by 하여 row 수를 줄인다.
    private BooleanExpression likeTag(String title){
        if(StringUtils.hasText(title)){
            return room.id.in(
                    JPAExpressions
                    .select(roomTag.room.id)
                    .from(roomTag)
                    .where(roomTag.name.like(title))
                    .groupBy(roomTag.room.id)
            );
        }
        return null;
    }
    // TODO: 2022-10-11 point type을 이용한 좌표조회로 변경 필요
}
