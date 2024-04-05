package org.gatewaymicroservice.config;

import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.gatewaymicroservice.constants.MSConstant.*;

@Configuration
@AllArgsConstructor
public class ConfigRouter {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()

                .route(USER_MICROSERVICE, r -> r.path("/users/**")

                        .uri("lb://user-microservice"))

                .route(CATALOGUE_MICROSERVICE, r -> r.path("/products/**")

                        .uri("lb://catalogue-microservice"))

                .route(PAYMENT_MICROSERVICE, r -> r.path("/users/**")

                        .uri("lb://payment-microservice"))

                .route(ORDER_MICROSERVICE, r -> r.path("/order/**")

                        .uri("lb://order-microservice"))

                .route(AUTH_MICROSERVICE, r -> r.path("/auth/**")

                        .uri("lb://security-microservice"))

                .build();
    }
}
