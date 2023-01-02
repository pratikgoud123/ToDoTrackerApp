/*
 *Author Name: Nikita Chauhan
 *Date: 12-12-2022
 *Created With: IntelliJ IDEA Community Edition
 */


package com.niit.SpringCloudApiGateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/api/v1/**")
                        .uri("http://customer-service:8083/")) // use the name of the application in the uri

                .route(p->p
                        .path("/api/v2/**")
                        .uri("http://authentication-service:8086/"))
                .build();
    }
}
