package com.metacrew.pr2s.dto.condition;

import com.metacrew.pr2s.dto.RoomUsageDto;
import com.metacrew.pr2s.entity.enums.Usage;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 방 조회시 사용되는 검색 조건 DTO.
 * @author sunggyu
 * @since 2022.10.07
 */
@Getter @Setter @ToString
public class RoomSearchConditionDto {
    // TODO: 2022-10-07 사용자 등급 별로 조회가 달라지는데 그의 따라 검색 조건도 나누어야 하는지 고민이 필요
    //사용인원
    @NumberFormat
    private Integer maximumPersonCount;

    //x좌표
    private String entX;

    //y좌표
    private String entY;

    //예약 시작 시간
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    //제목
    private String title;

    //시도
    private String siNm;
    //시군구
    private String sggNm;

    private List<Usage> usages;
}
