package com.metacrew.pr2s.controller;

import com.metacrew.pr2s.config.annotation.FileRequestMapping;
import com.metacrew.pr2s.service.storageservice.FilePath;
import com.metacrew.pr2s.service.storageservice.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HelloController {
    private final StorageService storageService;

    @GetMapping("/hello")
    public String hello(){
        return "common/body/main";
    }

    @PostMapping("/testfile")
    public String test(@RequestAttribute List<String> fileIds){
        for(String id : fileIds){
            log.info(id);
        }
        return "common/body/main";
    }

    @FileRequestMapping(value = "/testfile2"
            , uploadPath = FilePath.upload_admin
            , mediaTypes = {MediaType.TEXT_PLAIN_VALUE, MediaType.IMAGE_JPEG_VALUE}
            , maxCount = 2)
    public String handleFileUpload(@RequestAttribute List<Long> fileIds) {
        for(Long id: fileIds){
            log.info("파일 id 확인:{}", fileIds);
        }
        return "redirect:/common/body/main";
    }
}
