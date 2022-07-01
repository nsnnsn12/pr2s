package com.metacrew.pr2s.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 시스템 내의 기관운영요일을 표현하는 enum 클래스이다.
 * @author nahyun
 * @since 2022.06.30
 */
@Getter
public enum WorkDay {
    SUN("일"),
    MON("월"),
    TUE("화"),
    WED("수"),
    THU("목"),
    FRI("금"),
    SAT("토");

    private final String day;

    WorkDay(String day) {
        this.day=day;
    }

    public String day() {
        return day;
    }
}
