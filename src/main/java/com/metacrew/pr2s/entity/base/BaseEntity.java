package com.metacrew.pr2s.entity.base;

import lombok.Getter;
import org.apache.tomcat.jni.Local;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public class BaseEntity extends BaseTimeEntity{
    @Column
    private boolean isDeleted;

    @Column
    private LocalDateTime deletedDate;

    /**
     * 엔터티를 삭제 상태로 변경
     * @author hyeonwoo
     * @since 2022.07.10
     */
    public void deleted(){
        isDeleted = true;
        deletedDate = LocalDateTime.now();
    }
}
