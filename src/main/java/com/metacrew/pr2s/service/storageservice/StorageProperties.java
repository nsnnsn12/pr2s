package com.metacrew.pr2s.service.storageservice;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.file.Path;
import java.util.Map;

@ConfigurationProperties("storage")
@Getter @Setter
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String rootLocation;
}