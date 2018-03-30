package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * SpringBoot项目启动类
 */
@SpringBootApplication
public class SpringbootFirstbloodApplication {

	private static Logger logger = LoggerFactory.getLogger(SpringbootFirstbloodApplication.class);
	public static void main(String[] args) {
		logger.info("【-- 项目开始启动 --】");
		SpringApplication.run(SpringbootFirstbloodApplication.class, args);
		logger.info("【-- 项目启动完成 --】");
	}
}
