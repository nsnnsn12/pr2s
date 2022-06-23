package com.metacrew.pr2s.entity.base;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public class BaseEntity extends BaseTimeEntity{
    @Column
    private Boolean isDeleted;

    @Column
    private LocalDateTime deletedDate;
}
