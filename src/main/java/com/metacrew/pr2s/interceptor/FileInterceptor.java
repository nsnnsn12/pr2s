package com.metacrew.pr2s.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
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
}
