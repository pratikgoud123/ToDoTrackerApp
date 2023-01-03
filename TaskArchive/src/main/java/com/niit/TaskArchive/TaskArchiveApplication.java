package com.niit.TaskArchive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TaskArchiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskArchiveApplication.class, args);
	}

}
