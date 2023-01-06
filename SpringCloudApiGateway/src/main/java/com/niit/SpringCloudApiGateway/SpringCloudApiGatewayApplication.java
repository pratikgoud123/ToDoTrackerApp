package com.niit.SpringCloudApiGateway;

import com.niit.SpringCloudApiGateway.filter.Filter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@EnableEurekaClient
public class SpringCloudApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudApiGatewayApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean jwtFilter()
	{
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new Filter());
		filterRegistrationBean.addUrlPatterns("/api/ex/**");
		return filterRegistrationBean;

	}

}
