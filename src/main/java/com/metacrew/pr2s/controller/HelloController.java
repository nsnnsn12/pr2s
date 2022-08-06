package com.metacrew.pr2s.controller;

import com.metacrew.pr2s.service.storage.StorageFileNotFoundException;
import com.metacrew.pr2s.service.storage.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
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
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/common/body/main";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
