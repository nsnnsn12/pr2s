package com.metacrew.pr2s.dto;

import lombok.*;


@Getter @Setter
@ToString
public class FileInfoDto {
    private Long id;
    private String name;
    private String path;
    private String fileType;
    private String realName;
    private long size;
}
