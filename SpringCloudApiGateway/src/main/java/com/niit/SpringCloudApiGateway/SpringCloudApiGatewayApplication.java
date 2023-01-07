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
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("/api/**").uri("http://tracker-service:8083/")) // use the name of the application in the uri

				.route(p -> p.path("/api/**").uri("http://authentication-service:8086/"))

				.route(p -> p.path("/api/**").uri("http://archive-service:8081/"))

				.route(p -> p.path("/api/**").uri("http://notification-service:8082/"))

				.build();
	}

	@Bean
	public FilterRegistrationBean jwtFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new Filter());
		filterRegistrationBean.addUrlPatterns("/api/v1","/api/v1/*/*",
												"/api/v2","/api/v2/*",
												"/api/v3","/api/v3/*/*",
												"/api/v4","/api/v4/*/*");
		return filterRegistrationBean;

	}

}
