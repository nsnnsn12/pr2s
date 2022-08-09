package com.metacrew.pr2s.interceptor;

import com.metacrew.pr2s.service.storageservice.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
public class FileInterceptor implements HandlerInterceptor {
    private final StorageService storageService;
    static final String[] FILE_URI = {"/testfile"};
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("URI정보 : {}", requestURI);
        if(isFileUri(requestURI)){
            //해당 메소드에서는 루트 path를 기준으로 입력받은 파라미터를 기준으로 파일을 저장하기만 한다.
            //파일의 path를 결정하고 validation하는 것은 다른 쪽에서 해야 하는 것 아닌가?
            //그럼 path와 validation을 어디서 정해야 하는가?
            //url에 따라 validation과 파일 경로가 달라진다.
            //그말은 intercepter에서 결정해야 한다.
            log.info("파일 정보가 들어있는 URI입니다.");
            MultipartHttpServletRequest multipartHttpServletRequest =  (MultipartHttpServletRequest) request;
            MultipartFile file = multipartHttpServletRequest.getFile("file");
            String fileName = storageService.store(file, "admin");
            // TODO: 2022-08-06 파일업로드 service 로직 필요
            request.setAttribute("fileIds", 1L);
            //MultipartHttpServletRequest multipartHttpServletRequest =  (MultipartHttpServletRequest) request;
        }
        //request.
        /*
        1. url이 파일업로드 처리가 필요한 url인지 확인한다.
        1.  MultipartFile[]를 가져온다.
        2. url에 맞는 validation 체크를 한다.
        3. 해당 정보를 이용하여 fileUpload처리를 한다.
        4. file id list 혹은 fild id를 세팅한다.
         */
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    public boolean isFileUri(String requestURI){
        for(String fileUri : FILE_URI){
            if(fileUri.equals(requestURI)) return true;
        }
        return false;
    }
}
