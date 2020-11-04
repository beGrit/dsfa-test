package com.dsfa.platform.service.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 平台核心服务
 * @author wenjin
 */
@SpringBootApplication(scanBasePackages = "com.dsfa")
public class DsfaPlatformServiceCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(DsfaPlatformServiceCoreApplication.class, args);
	}

}
