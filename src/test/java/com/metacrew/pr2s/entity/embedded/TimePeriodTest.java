package com.metacrew.pr2s.entity.embedded;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class TimePeriodTest {
    @Test
    @DisplayName("운영시간 생성 테스트")
    public void createTimePeriodTest() {
        // given
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(22, 0);

        // when
        TimePeriod testTimePeriod = TimePeriod.createTimePeriod(startTime, endTime);

        // then
        assertThat(testTimePeriod.getStartTime()).isEqualTo(startTime);
        assertThat(testTimePeriod.getEndTime()).isEqualTo(endTime);
    }

    @Test
    @DisplayName("유효하지 않은 운영시간 생성 테스트")
    public void createInvalidTimePeriodTest() {
        // given
        LocalTime startTime = LocalTime.of(10, 0);
        LocalTime endTime = LocalTime.of(8, 0);

        // when
        assertThatThrownBy(() -> TimePeriod.createTimePeriod(startTime, endTime)
        ).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("종료 시간이 사작 시간보다 이전입니다.");
    }

}