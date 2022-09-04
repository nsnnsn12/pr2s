package com.metacrew.pr2s.service.roomservice;

import com.metacrew.pr2s.dto.AddressDto;
import com.metacrew.pr2s.dto.RoomDto;
import com.metacrew.pr2s.entity.FileInfo;
import com.metacrew.pr2s.entity.Room;

import java.util.List;

/**
 * 방 정보를 관리하기 위한 메소드를 정의한 인터페이스이다.
 * @author nahyun
 * @since 2022.07.16
 */

public interface RoomService {


    /**
     * 방 등록
     * 방등록용 DTO와 Address Dto를 입력받아 방 등록 처리하고 방 정보를 리턴한다.
     * @param roomDto 회원 정보.
     * @param addressDto 주소 정보
     * @return 방정보
     * @author nahyun
     * @since 2022.07.16
     */
    Room register(RoomDto roomDto, AddressDto addressDto, List<FileInfo> fileInfoList);

}
