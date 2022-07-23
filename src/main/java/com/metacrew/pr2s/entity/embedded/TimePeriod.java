package com.metacrew.pr2s.entity.embedded;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalTime;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TimePeriod {
    private LocalTime startTime;
    private LocalTime endTime;
    
    /**
     * TimePeriod 생성 메소드
     * 시작, 종료 시간을 받아
     * 시간 정보가 유효하지 않으면 예외를 던지고
     * 유효한 시간 정보라면 TimePeriod 생성
     * @author hyeonwoo
     * @since 2022.07.23
     * @param startTime 운영시작시간
     * @param endTime 운영종료시간
     * @return TimePeriod 운영시각 생성
     */
    public static TimePeriod createTimePeriod(LocalTime startTime, LocalTime endTime) {
        checkValidTime(startTime, endTime);

        TimePeriod timePeriod = new TimePeriod();
        timePeriod.startTime = startTime;
        timePeriod.endTime = endTime;
        return timePeriod;
    }

    /**
     * TimePeriod 유효성 검사
     * 시작, 종료 시간을 받아
     * 종료 시간이 시작 시간보다 이전이라면 예외를 던진다.
     * @author hyeonwoo
     * @since 2022.07.23
     * @param startTime 운영시작시간
     * @param endTime 운영종료시간
     */
    public static void checkValidTime(LocalTime startTime, LocalTime endTime) {
        // 시작 종료 시간 유효성 검사
        if(startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("종료 시간이 사작 시간보다 이전입니다.");
        }
    }
}
