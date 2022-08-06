package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.entity.enums.FileType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FileTest {
    @Test
    @DisplayName("파일 객체 생성")
    void createFile() {
        // given
        String name = "노성규";
        String source = "/photo";
        FileType fileType = null;
        // when
        File file = File.createFile(name, source, fileType);
        // then
        assertThat(file.getName()).isEqualTo(name);
        assertThat(file.getPath()).isEqualTo(source);
        assertThat(file.getFileType()).isEqualTo(fileType);
    }
}