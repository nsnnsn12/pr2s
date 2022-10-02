package com.metacrew.pr2s.config;

import com.metacrew.pr2s.config.interceptor.FileInterceptor;
import com.metacrew.pr2s.repository.FileInfoRepository;
import com.metacrew.pr2s.service.storageservice.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { "classpath:/static/" };
    private final StorageService storageService;
    private final FileInfoRepository fileInfoRepository;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new FileInterceptor(storageService, fileInfoRepository))
                .addPathPatterns("/*");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // /에 해당하는 url mapping을 /common/test로 forward한다.
        registry.addViewController( "/" ).setViewName( "forward:/index" );
        // 우선순위를 가장 높게 잡는다.
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }
}
