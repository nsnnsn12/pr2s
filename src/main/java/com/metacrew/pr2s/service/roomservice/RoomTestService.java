package com.metacrew.pr2s.service.roomservice;


import com.metacrew.pr2s.dto.RoomDto;
import com.metacrew.pr2s.entity.Address;
import com.metacrew.pr2s.entity.FileInfo;
import com.metacrew.pr2s.entity.InstitutionAddress;
import com.metacrew.pr2s.entity.Room;
import com.metacrew.pr2s.repository.roomrepository.RoomRepository;
import com.metacrew.pr2s.repository.roomrepository.RoomTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomTestService{

    private final RoomTestRepository roomTestRepository;

    private final RoomRepository roomRepository;
    /**
     * Room 엔티티를 제목(방 명)을 입력받아 방을 조회한다
     * @author nahyun
     * @since 2022.07.01
     * @param title
     * @return List<Room>
     */
    public void searchRoomByTitle(String title){
        List<Room> list = roomTestRepository.findByRoomTitle(title);
    }

    /**
     * Room 전체 방을 조회한다
     * @author nahyun
     * @since 2022.07.02
     * @return List<Room>
     */

    public void  searchRoom(){
        List<Room> list = roomTestRepository.findAllRoom();
    }

    /**
     * Room 엔티티를 입력받아 roomId의 중복을 확인하고
     * 중복된 경우는 예외를 던지고
     * 중복이 아닌 경우는 엔티티를 DB에 저장하고 key값을 리턴한다.
     * @author nahyun
     * @since 2022.07.02
     * @param  roomDto
     * @return key값
     */
    public Room registerRoom(RoomDto roomDto, InstitutionAddress institutionAddress, List<FileInfo> fileInfoList){
        fileInfoList =null;
        validateDuplicateRoom(roomDto.getId());
        Room room=Room.createRoomByRoomDto(roomDto,institutionAddress);
        return roomRepository.save(room);
        //return roomTestRepository.register(room);
    }

    private void validateDuplicateRoom(Long roomId){
        List<Room> result = roomRepository.findByRoomListLongId(roomId);
        if(result.size() > 0) throw new IllegalStateException("이미 존재하는 방입니다.");
    }

    /**
     * Room 엔티티를 입력받아 방을 조회하고
     * 엔티티를 수정한 뒤 DB에 저장하고 key값을 리턴한다.
     * @author nahyun
     * @since 2022.07.02
     * @param roomDto
     * @return key값
     */

    public Long updateRoom(RoomDto roomDto, Long id){
        //file=null;
        Room findRoom = roomTestRepository.findByRoomLongId(id);
        findRoom.setForUpdateRoom(roomDto);
        return findRoom.getId();
    }

    /**
     * Room 엔티티를 입력받아 방을 조회하고
     * 엔티티를의 삭제여부를 DB에서 확인해 삭제된 게시글이 아닐경우
     * 삭제여부와 삭제시간을 수정하고 key값을 리턴한다.
     * @author nahyun
     * @since 2022.07.02
     * @param id
     * @return
     */
    public void deleteRoom(Long id){
        Room findRoom = roomTestRepository.findByRoomLongId(id);
        if(!findRoom.isDeleted()) findRoom.deleted();
      }


}
