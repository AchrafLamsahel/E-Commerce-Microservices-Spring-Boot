package org.gatewaymicroservice.config;

import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ConfigRouter {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()

                .route("user-microservice", r -> r.path("/users/**")
                        .uri("lb://user-microservice"))

                .route("catalogue-microservice", r -> r.path("/products/**")
                        .uri("lb://catalogue-microservice"))

                .route("payment-microservice", r -> r.path("/users/**")
                        .uri("lb://payment-microservice"))

                .route("order-microservice", r -> r.path("/order/**")
                        .uri("lb://order-microservice"))

                .route("security-microservice", r -> r.path("/auth/**")
                        .uri("lb://security-microservice"))

                .build();
    }
}
