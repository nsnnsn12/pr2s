package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.entity.base.BaseEntity;
import com.metacrew.pr2s.entity.enums.FileType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * File 테이블과 매핑되는 엔티티이다.
 * @author hyeonwoo
 * @since 2022.06.23
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FileInfo extends BaseEntity {
    @Id
    @Column(name = "file_id") @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String path;

    @Enumerated(EnumType.STRING)
    @Column
    private FileType fileType;

    public static FileInfo createFile(String name, String path, FileType fileType){
        FileInfo fileInfo = new FileInfo();
        fileInfo.path = path;
        fileInfo.name = name;
        fileInfo.fileType = fileType;
        return fileInfo;
    }
}
