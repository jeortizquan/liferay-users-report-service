package com.lighthouse.UsersReportService;

import com.lighthouse.config.UsersReportServiceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(UsersReportServiceConfig.class)
@ComponentScan("com.lighthouse")
public class UsersReportServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersReportServiceApplication.class, args);
	}

}
