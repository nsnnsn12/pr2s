package com.metacrew.pr2s.entity.base;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
public class BaseEntity extends BaseTimeEntity{
    private boolean isDeleted;
}
