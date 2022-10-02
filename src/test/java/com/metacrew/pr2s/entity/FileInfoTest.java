package com.metacrew.pr2s.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FileInfoTest {
    @Test
    @DisplayName("파일 객체 생성")
    void createFile() {
        // given
        String name = "노성규";
        String realName = "노성규";
        String source = "/photo";
        String fileType = "img";
        long size = 1024L;

        // when
        FileInfo fileInfo = FileInfo.createFile(name, realName, source, fileType, size);

        // then
        assertThat(fileInfo.getName()).isEqualTo(name);
        assertThat(fileInfo.getRealName()).isEqualTo(realName);
        assertThat(fileInfo.getPath()).isEqualTo(source);
        assertThat(fileInfo.getFileType()).isEqualTo(fileType);
        assertThat(fileInfo.getSize()).isEqualTo(size);
    }
}