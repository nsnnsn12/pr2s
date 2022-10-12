package com.metacrew.pr2s.repository.roomrepository;

import com.metacrew.pr2s.dto.condition.JoinInfoConditionDto;
import com.metacrew.pr2s.dto.condition.RoomSearchConditionDto;
import com.metacrew.pr2s.dto.room.SearchedRoomDto;
import com.metacrew.pr2s.entity.Institution;
import com.metacrew.pr2s.entity.JoinInfo;
import com.metacrew.pr2s.entity.Room;

import java.util.List;

public interface RoomQueryRepository {
    List<Room> search(RoomSearchConditionDto roomSearchConditionDto);
}
