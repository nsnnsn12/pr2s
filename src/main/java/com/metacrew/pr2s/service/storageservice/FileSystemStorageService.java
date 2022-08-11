package com.metacrew.pr2s.service.storageservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@Slf4j
public class FileSystemStorageService implements StorageService{
    private final Path rootLocation;
    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        // TODO: 2022-08-09 프로퍼티 수정 필요
        rootLocation = Paths.get(properties.getRootLocation());
    }

    @Override
    public String store(MultipartFile file, FilePath path) {
        try {
            String result;
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }

            String[] fileNames = file.getOriginalFilename().split("/");
            if(fileNames.length > 1){
                throw new StorageException("Cannot store folder.");
            }
            String fileName = UUID.randomUUID().toString()+file.getOriginalFilename();
            log.info("파일이름확인{}",fileName);
            Path subPath = Path.of(path.toString());
            Path destinationFile = rootLocation.resolve(subPath)
                                                .resolve(Paths.get(fileName))
                                                .normalize().toAbsolutePath();
            log.info("경로확인{}",destinationFile);
            if (!destinationFile.getParent().equals(rootLocation.resolve(subPath).toAbsolutePath())) {
                // This is a security check
                throw new StorageException("Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                log.info(destinationFile.toString());
                log.info("파일이름:{}", destinationFile.getFileName());
                result = destinationFile.getFileName().toString();
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }

            return result;
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }

    /*
    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }
    @Override
    public Path load(String filename, String type) {
        return this.locations.get(type).resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename, String type) {
        try {
            Path file = load(filename, type);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    */

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            for(FilePath filePath : FilePath.values()){
                Path subPath = Path.of(filePath.toString());
                Path destinationFile = rootLocation.resolve(subPath);
                Files.createDirectories(destinationFile);
            }
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}
