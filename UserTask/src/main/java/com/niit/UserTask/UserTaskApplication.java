package com.niit.UserTask;

import com.niit.UserTask.fillter.Filter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class UserTaskApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserTaskApplication.class, args);
	}



	@Bean
	public FilterRegistrationBean jwtFilter()
	{
		FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new Filter());
		filterRegistrationBean.addUrlPatterns("/api/v1/task/*");

		return filterRegistrationBean;
	}
}
