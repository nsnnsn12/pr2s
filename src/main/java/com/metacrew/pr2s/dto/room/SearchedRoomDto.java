package com.metacrew.pr2s.dto.room;

import com.metacrew.pr2s.entity.Address;
import com.metacrew.pr2s.entity.Institution;
import com.metacrew.pr2s.entity.enums.Usage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchedRoomDto {
    //id
    private Long id;

    //사진 정보
    private Long fileId;

    //타이틀
    private String title;

    // TODO: 2022-10-10 태그

    //최대 인원 수
    private int maximumPersonCount;

    //댓글 수
    private int replyCount;

    //즐겨찾기 수
    private int favoriteCount;

    //시군구명
    private String sigunguNm;
    
    //마감여부
    private boolean isDeadLine;
}
