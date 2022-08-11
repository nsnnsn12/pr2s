package com.metacrew.pr2s.service.storageservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;


import static org.junit.jupiter.api.Assertions.*;

class FileSystemStorageServiceTest {
    private final StorageProperties properties = new StorageProperties();
    private FileSystemStorageService service;
    @BeforeEach
    public void init() {
        properties.setRootLocation("files");
        service = new FileSystemStorageService(properties);
        service.deleteAll();
        service.init();
    }

    @Test
    public void saveAbsolutePathNotPermitted() {
        MockMultipartFile foo = new MockMultipartFile("foo"
                , "/etc/passwd"
                , MediaType.TEXT_PLAIN_VALUE
                , "Hello, World".getBytes());
        assertThrows(StorageException.class, () -> service.store(foo, FilePath.upload_test));
    }

    @Test
    public void saveRelativePathNotPermitted() {
        MockMultipartFile foo = new MockMultipartFile("foo"
                , "../foo.txt"
                , MediaType.TEXT_PLAIN_VALUE
                , "Hello, World".getBytes());
        assertThrows(StorageException.class, () -> service.store(foo, FilePath.upload_test));
    }

    @Test
    public void saveFolderNotPermitted() {
        MockMultipartFile foo = new MockMultipartFile("foo"
                , "bar/../foo.txt"
                , MediaType.TEXT_PLAIN_VALUE
                , "Hello, World".getBytes());

        assertThrows(StorageException.class, () -> service.store(foo, FilePath.upload_test));
    }

    @Test
    public void savePermitted() {
        service.store(new MockMultipartFile("foo", "foo.txt",
                MediaType.TEXT_PLAIN_VALUE, "Hello, World".getBytes()), FilePath.upload_test);
    }

/*
    @Test
    public void loadNonExistent() {
        assertThat(service.load("foo.txt")).doesNotExist();
    }

    @Test
    public void saveAndLoad() {
        service.store(new MockMultipartFile("foo", "foo.txt", MediaType.TEXT_PLAIN_VALUE,
                "Hello, World".getBytes()));
        assertThat(service.load("foo.txt")).exists();
    }
    */
}