package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.entity.base.BaseEntity;
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

    @Column
    private String fileType;

    @Column
    private String realName;

    @Column
    private long size;

    public static FileInfo createFile(String name, String realName,String path, String fileType, long size){
        FileInfo fileInfo = new FileInfo();
        fileInfo.name = name;
        fileInfo.path = path;
        fileInfo.fileType = fileType;
        fileInfo.realName = realName;
        fileInfo.size = size;

        return fileInfo;
    }
}
