package com.metacrew.pr2s.repository.roomtagrepository;

import com.metacrew.pr2s.entity.Room;
import com.metacrew.pr2s.entity.RoomTag;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import java.util.List;

import static com.metacrew.pr2s.entity.QRoomTag.roomTag;

public class RoomTagQueryRepositoryImpl implements RoomTagQueryRepository {
    private final JPAQueryFactory queryFactory;
    public RoomTagQueryRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<RoomTag> searchByRoomId(Room room) {
        return queryFactory
                .select(roomTag)
                .from(roomTag)
                .where(roomEq(room))
                .fetch();
    }

    private BooleanExpression roomEq(Room room){
        return room != null ? roomTag.room.eq(room) : null;
    }
}
