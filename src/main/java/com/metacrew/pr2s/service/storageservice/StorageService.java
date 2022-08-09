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
     * @return 파일이름
     * @throws IllegalStateException 존재하지 않는 파일정보에 대해 요청할 경우
     * @throws IllegalStateException 이미 존재하는 login id를 사용하려는 경우
     * @since 2022.08.09
     * @author sunggyu
     */
    String store(MultipartFile file, String type);

    //Stream<Path> loadAll();

    Path load(String filename, String type);

    Resource loadAsResource(String filename, String type);

    void deleteAll();

}