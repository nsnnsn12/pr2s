package com.metacrew.pr2s.config;

import com.metacrew.pr2s.interceptor.FileInterceptor;
import com.metacrew.pr2s.interceptor.FileUriProperties;
import com.metacrew.pr2s.service.storageservice.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final StorageService storageService;
    private final FileUriProperties fileUriProperties;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new FileInterceptor(storageService, fileUriProperties))
                .addPathPatterns("/*");
    }
}
