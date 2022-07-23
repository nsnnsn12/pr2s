package com.metacrew.pr2s.service.roomservice;

import com.metacrew.pr2s.dto.AddressDto;
import com.metacrew.pr2s.dto.FileDto;
import com.metacrew.pr2s.dto.RoomDto;
import com.metacrew.pr2s.entity.Address;
import com.metacrew.pr2s.entity.File;
import com.metacrew.pr2s.entity.Institution;
import com.metacrew.pr2s.entity.Room;
import com.metacrew.pr2s.repository.AddressRepository;
import com.metacrew.pr2s.repository.institutionrepository.InstitutionRepository;
import com.metacrew.pr2s.repository.roomrepository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    private final AddressRepository addressRepository;

    /**
     * 방 정보를 입력받아 엔터티를 DB에 저장하고 key 값을 반환
     * @author nahyun
     * @since 2022.07.16
     * @param roomDto 등록할 방 정보.
     * @param addressDto 등록할 방의 주소 정보.
     * @return 등록한 방 key 값.
     */
    @Override
    public Room register(RoomDto roomDto, AddressDto addressDto, List<File> fileList) {


        Address address = addressRepository.save(Address.createAddressByAddressDto(addressDto));
        Room room = Room.createRoomByRoomDto(roomDto,address);
        Room savedRoom = roomRepository.save(room);
        // TODO: 2022-07-23 RoomImage 구현 필요
//        for(File file : fileList){
//            RoomImage roomImage = RoomImage.createRoomImage(savedRoom, file);
//            roomImageRepository.save(roomImage);
//        }

        //룸을 등록한다고 했을 때 사진을 하나만 등록할 수 있도록 할 것인지 생각 필요

        return room;
    }

    // TODO: 2022-07-21 방 등록시 기관 소속 여부 인증 방법
    // TODO: 2022-07-21 기관 소속 인증 이후 방 등록시 중복 여부 확인 방법
}
