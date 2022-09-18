package com.metacrew.pr2s;

import com.metacrew.pr2s.service.storageservice.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableCaching
public class Pr2sApplication {

	public static void main(String[] args) {
		SpringApplication.run(Pr2sApplication.class, args);
	}



}
