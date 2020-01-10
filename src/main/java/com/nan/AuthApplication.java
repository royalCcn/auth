package com.nan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ServletComponentScan
@EnableCaching
@EnableSwagger2
@EnableScheduling
@EnableAsync
public class AuthApplication {

	private static final Logger logger = LoggerFactory.getLogger(AuthApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
		logger.error("==============开启==============");
	}

}
