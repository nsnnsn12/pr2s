package com.metacrew.pr2s;

import com.metacrew.pr2s.service.storageservice.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class Pr2sApplication {

	public static void main(String[] args) {
		SpringApplication.run(Pr2sApplication.class, args);
	}



}
