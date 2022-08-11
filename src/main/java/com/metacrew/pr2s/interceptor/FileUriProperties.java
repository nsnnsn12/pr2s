package com.metacrew.pr2s.interceptor;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("file-uri")
@Component
@Getter @Setter
public class FileUriProperties {
    private String[] uris;
}