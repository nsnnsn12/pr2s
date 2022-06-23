package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.entity.base.BaseEntity;
import com.metacrew.pr2s.entity.enums.FileType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class File extends BaseEntity {
    @Id
    @Column(name = "file_id") @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String source;

    @Enumerated(EnumType.STRING)
    @Column
    private FileType fileType;
}
