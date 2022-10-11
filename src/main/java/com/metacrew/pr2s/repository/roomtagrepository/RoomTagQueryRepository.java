package com.metacrew.pr2s.repository.roomtagrepository;

import com.metacrew.pr2s.entity.Room;
import com.metacrew.pr2s.entity.RoomTag;
import com.metacrew.pr2s.entity.RoomUsage;

import java.util.List;

public interface RoomTagQueryRepository {
    List<RoomTag> searchByRoomId(Room room);
}
