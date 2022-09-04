package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.entity.enums.FileType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FileInfoTest {
    @Test
    @DisplayName("파일 객체 생성")
    void createFile() {
        // given
        String name = "노성규";
        String source = "/photo";
        FileType fileType = null;
        // when
        FileInfo fileInfo = FileInfo.createFile(name, source, fileType);
        // then
        assertThat(fileInfo.getName()).isEqualTo(name);
        assertThat(fileInfo.getPath()).isEqualTo(source);
        assertThat(fileInfo.getFileType()).isEqualTo(fileType);
    }
}