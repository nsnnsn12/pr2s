package com.metacrew.pr2s.dto;

import com.metacrew.pr2s.entity.base.BaseEntity;
import com.metacrew.pr2s.entity.enums.FileType;
import lombok.*;

import javax.persistence.*;


@Getter @Setter
@ToString
public class FileInfoDto {
    private Long id;
    private String name;
    private String path;
    private String fileType;
    private String realName;
}
