package com.metacrew.pr2s.controller;

import com.metacrew.pr2s.dto.FileInfoDto;
import com.metacrew.pr2s.service.storageservice.StorageFileNotFoundException;
import com.metacrew.pr2s.service.storageservice.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping("/testfile2")
    public String handleFileUpload(RedirectAttributes redirectAttributes
            , @ModelAttribute FileInfoDto fileInfoDto
            , MultipartFile file) {
        log.info(fileInfoDto.toString());
        fileInfoDto.setFileType(file.getContentType());
        fileInfoDto.setName(file.getOriginalFilename());
        String fileName = storageService.store(file, "upload_board");
        fileInfoDto.setRealName(fileName);
        log.info(fileInfoDto.toString());
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        return "redirect:/common/body/main";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
