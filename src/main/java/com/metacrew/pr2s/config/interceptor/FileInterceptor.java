package com.metacrew.pr2s.config.interceptor;

import com.metacrew.pr2s.config.annotation.FileRequestMapping;
import com.metacrew.pr2s.entity.FileInfo;
import com.metacrew.pr2s.repository.FileInfoRepository;
import com.metacrew.pr2s.service.storageservice.FilePath;
import com.metacrew.pr2s.service.storageservice.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
public class FileInterceptor implements HandlerInterceptor {
    private final StorageService storageService;
    private final FileInfoRepository fileInfoRepository;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        FileRequestMapping fileRequestMapping = handlerMethod.getMethodAnnotation(FileRequestMapping.class);
        if(fileRequestMapping == null) {
            return true;
        }

        String[] validContentTypes = fileRequestMapping.mediaTypes();
        for(String str : validContentTypes){
            log.info("미디어타입:{}", str);
        }
        int maxCount = fileRequestMapping.maxCount();
        log.info("최대갯수:{}", maxCount);
        FilePath filePath = fileRequestMapping.uploadPath();
        log.info("업로드 경로:{}", filePath);
        long maxSize = fileRequestMapping.maxSize();
        log.info("파일 최대 크기:{}", maxSize);

        MultipartHttpServletRequest multipartHttpServletRequest =  (MultipartHttpServletRequest) request;
        Iterator<String> fileNames = multipartHttpServletRequest.getFileNames();
        List<MultipartFile> multipartFiles = getMultipartFiles(fileNames, multipartHttpServletRequest);
        List<Long> fileIds = new ArrayList<>();
        if(multipartFiles.size() == 0){
            request.setAttribute("fileIds", fileIds);
            return true;
        }

        if(maxCount < multipartFiles.size()){
            throw new IllegalStateException("업로드 가능한 파일 갯수를 초과하였습니다");
        }

        for(MultipartFile file : multipartFiles){
            long fileSize = file.getSize();
            if(fileSize > maxSize) throw new IllegalStateException("업로드 가능한 파일 크기를 넘었습니다.");

            String contentType = file.getContentType() == null ? "" : file.getContentType();
            if(!isValid(contentType, validContentTypes)){
                throw new IllegalStateException("업로드 가능한 미디어 타입이 아닙니다.");
            }
        }
        for(MultipartFile file : multipartFiles){
            String fileName = file.getOriginalFilename();
            String fileRealName = storageService.store(file, filePath);
            String path = filePath.toString();
            long fileSize = file.getSize();
            String contentType = file.getContentType();
            FileInfo fileInfo = FileInfo.createFile(fileName, fileRealName, path, contentType, fileSize);
            FileInfo save = fileInfoRepository.save(fileInfo);
            fileIds.add(save.getId());
        }
        request.setAttribute("fileIds", fileIds);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    public boolean isValid(String contentType, String[] validContentTypes){
        for(String validType : validContentTypes){
            if(contentType.equals(validType)) return true;
        }

        return false;
    }

    public List<MultipartFile> getMultipartFiles(Iterator<String> fileNames, MultipartHttpServletRequest multipartHttpServletRequest){
        List<MultipartFile> multipartFiles = new ArrayList<>();
        while(fileNames.hasNext()){
            String fileName = fileNames.next();
            List<MultipartFile> files = multipartHttpServletRequest.getFiles(fileName);
            if(!files.isEmpty()){
                multipartFiles.addAll(files);
                continue;
            }

            MultipartFile file = multipartHttpServletRequest.getFile(fileName);
            if(file != null) multipartFiles.add(file);
        }
        return multipartFiles;
    }
}
