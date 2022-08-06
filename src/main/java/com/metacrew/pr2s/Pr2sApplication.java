package com.metacrew.pr2s;

import com.metacrew.pr2s.service.storage.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Pr2sApplication {

	public static void main(String[] args) {
		SpringApplication.run(Pr2sApplication.class, args);
	}



}
