package com.metacrew.pr2s.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

@Slf4j
public class FileInterceptor implements HandlerInterceptor {
    static final String[] FILE_URI = {"/testfile"};
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("URI정보 : {}", requestURI);
        if(isFileUri(requestURI)){
            log.info("파일 정보가 들어있는 URI입니다.");
            MultipartHttpServletRequest multipartHttpServletRequest =  (MultipartHttpServletRequest) request;
            Iterator<String> fileNames = multipartHttpServletRequest.getFileNames();

            List<String> fileIds = new ArrayList<>();
            fileNames.forEachRemaining(file -> fileIds.add(file.toLowerCase(Locale.ROOT)));
            request.setAttribute("fileIds", fileIds);
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
