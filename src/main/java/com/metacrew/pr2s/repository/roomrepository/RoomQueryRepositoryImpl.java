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
import static com.metacrew.pr2s.entity.QRoomUsage.roomUsage;


public class RoomQueryRepositoryImpl implements RoomQueryRepository {
    private final JPAQueryFactory queryFactory;
    public RoomQueryRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }
    @Override
    public List<SearchedRoomDto> search(RoomSearchConditionDto roomSearchConditionDto) {
        //리턴값을 dto로 받을 것인가? 일단 dto로 받고 해당 기능이 더 확장되면 다시 entity로 받는 것으로 해야 할까?
        return queryFactory
                .select(Projections.constructor(SearchedRoomDto.class))
                .from(room)
                .join(room.institutionAddress, institutionAddress) //다대일
                .join(institutionAddress.address, address) //일대일
                .leftJoin(room, roomUsage.room) //일대다
                .where(room.isDeleted.eq(false)
                        , likeTitle(roomSearchConditionDto.getTitle())
                        , goeMaximumPersonCount(roomSearchConditionDto.getMaximumPersonCount())
                        , eqSiNm(roomSearchConditionDto.getSiNm())
                        , eqSggNm(roomSearchConditionDto.getSggNm())
                        , eqEmdNm(roomSearchConditionDto.getEmdNm())
                        , notInDatePeriod(roomSearchConditionDto.getStartDate(), roomSearchConditionDto.getEndDate())
                        , inUsage(roomSearchConditionDto.getUsages()))
                .fetch();
    }
/*
join시 사용돼야 하는 entity
room, address, reservation, usage 조인을 걸고 페이징 작업까지 해야 함.

    room 조건
    //제목
    private String title; -> 제목으로 태그도 검색해야 함.

    //사용인원
    private int maximumPersonCount;

    address 조건
    //x좌표
    private String entX;

    //y좌표
    private String entY;

    //시도
    private String siNm;

    //시군구
    private String sggNm;

    //읍면동
    private String emdNm;

    reservation 조건
    //예약 시작 시간
    private LocalDateTime startDate;

    //예약 종료 시간
    private LocalDateTime endDate;
*/
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
    private BooleanExpression inUsage(List<Usage> usages){
        return usages != null ? roomUsage.usage.in(usages): null;
    }

    //select id from reservation where startDate > sss and group by room id
    // TODO: 2022-10-11 태그 조건 필요

    // TODO: 2022-10-11 point type을 이용한 좌표조회로 변경 필요
}
