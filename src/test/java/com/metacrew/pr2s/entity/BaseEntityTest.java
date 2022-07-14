package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.entity.base.BaseEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class BaseEntityTest {
    @Test
    @DisplayName("베이스엔터티 삭제 테스트")
    public void deletedTest() {
        //given
        BaseEntity baseEntity = new BaseEntity();

        //when
        baseEntity.deleted();

        //then
        assertThat(baseEntity.isDeleted()).isEqualTo(true);
        assertThat(baseEntity.getDeletedDate()).isNotNull();
    }

}