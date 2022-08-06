package com.metacrew.pr2s.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Slf4j
public class HelloController {
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
}
