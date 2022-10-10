package com.metacrew.pr2s.repository.roomusagerepository;

import com.metacrew.pr2s.entity.Room;
import com.metacrew.pr2s.entity.RoomUsage;

import java.util.List;

public interface RoomUsageQueryRepository {
    List<RoomUsage> searchByRoomId(Room room);
}
