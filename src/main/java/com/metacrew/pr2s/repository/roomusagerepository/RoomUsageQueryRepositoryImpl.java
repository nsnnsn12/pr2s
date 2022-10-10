package com.metacrew.pr2s.repository.roomusagerepository;

import com.metacrew.pr2s.entity.Institution;
import com.metacrew.pr2s.entity.Room;
import com.metacrew.pr2s.entity.RoomUsage;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.metacrew.pr2s.entity.QRoomUsage.roomUsage;

public class RoomUsageQueryRepositoryImpl implements RoomUsageQueryRepository{
    private final JPAQueryFactory queryFactory;
    public RoomUsageQueryRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<RoomUsage> searchByRoomId(Room room) {
        return queryFactory
                .select(roomUsage)
                .from(roomUsage)
                .where(roomEq(room))
                .fetch();
    }

    private BooleanExpression roomEq(Room room){
        return room != null ? roomUsage.room.eq(room) : null;
    }
}
