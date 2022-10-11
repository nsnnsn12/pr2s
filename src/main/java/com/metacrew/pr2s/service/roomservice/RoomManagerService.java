package com.metacrew.pr2s.service.roomservice;

import com.metacrew.pr2s.dto.AddressDto;
import com.metacrew.pr2s.dto.RoomDto;
import com.metacrew.pr2s.dto.RoomTagDto;
import com.metacrew.pr2s.dto.RoomUsageDto;
import com.metacrew.pr2s.entity.*;
import com.metacrew.pr2s.repository.AddressRepository;
import com.metacrew.pr2s.repository.RoomImageRepository;
import com.metacrew.pr2s.repository.roomrepository.RoomRepository;
import com.metacrew.pr2s.repository.roomtagrepository.RoomTagRepository;
import com.metacrew.pr2s.repository.roomusagerepository.RoomUsageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 관리자가 방을 관리하기 위한 서비스 로직을 가지고 있는 클래스이다.
 * @author nahyun
 * @since 2022.07.16
 */
@Service
@Transactional
@RequiredArgsConstructor
public class RoomManagerService implements RoomService{
    private final RoomRepository roomRepository;

    private final RoomImageRepository roomImageRepository;

    private final RoomUsageRepository roomUsageRepository;

    private final RoomTagRepository roomTagRepository;

    /**
     * 방 정보를 입력받아 엔터티를 DB에 저장하고 key 값을 반환
     * @author nahyun
     * @since 2022.07.16
     * @param roomDto 방 정보.
     * @param institutionAddress 방의 주소 정보.
     * @param roomUsageDtoList 방의 사용용도 정보
     * @param roomTagDtoList 방의 태그 정보
     * @return 등록한 방 key 값.
     */
    @Override
    public Room register(RoomDto roomDto, InstitutionAddress institutionAddress, List<RoomUsageDto> roomUsageDtoList, List<RoomTagDto> roomTagDtoList) {
        Room room = Room.createRoomByRoomDto(roomDto, institutionAddress);
        Room savedRoom = roomRepository.save(room);

//        for(FileInfo file : fileInfoList){
//            RoomImage roomImage = RoomImage.createRoomImage(savedRoom, file);
//            roomImageRepository.save(roomImage);
//        }

        for(RoomUsageDto roomUsageDto : roomUsageDtoList) {
            roomUsageRepository.save(RoomUsage.createRoomUsage(roomUsageDto, savedRoom));
        }

        for(RoomTagDto roomTagDto : roomTagDtoList) {
            roomTagRepository.save(RoomTag.createRoomTag(roomTagDto, savedRoom));
        }

        return savedRoom;
    }
}
