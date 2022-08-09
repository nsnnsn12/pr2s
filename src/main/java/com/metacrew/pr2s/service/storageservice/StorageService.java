package com.metacrew.pr2s.service.storageservice;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    /**
     * 파일 저장
     * MultipartFile을 입력받아 파일 저장 후 파일 이름을 리턴한다.
     *
     * @param file 파일
     * @param path 저장할 경로
     * @return 파일이름
     * @throws StorageException
     * 입력받은 파일이 존재하지 않는 경우
     * 입력받은 경로가 올바르지 않은 경우
     * 파일을 저장이 실패하는 경우
     * @since 2022.08.09
     * @author sunggyu
     */
    String store(MultipartFile file, String path);

    //Stream<Path> loadAll();

    //Path load(String filename, String type);

    //Resource loadAsResource(String filename, String type);

    void deleteAll();

}