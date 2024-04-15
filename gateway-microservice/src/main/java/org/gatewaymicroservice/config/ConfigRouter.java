package org.gatewaymicroservice.config;

import lombok.AllArgsConstructor;
import org.gatewaymicroservice.filter.JwtAuthenticationFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.gatewaymicroservice.constants.MSConstant.*;

@Configuration
@AllArgsConstructor
public class ConfigRouter {
    private final JwtAuthenticationFilter filter;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()

                .route(USER_MICROSERVICE, r -> r.path("/users/**")
                        .filters(f->f.filter(filter))
                        .uri(USER_MICROSERVICE_URI))

                .route(CATALOGUE_MICROSERVICE, r -> r.path("/products/**")
                        .uri(CATALOGUE_MICROSERVICE_URI))

                .route(CATALOGUE_MICROSERVICE, r -> r.path("/categories/**")
                        .uri(CATALOGUE_MICROSERVICE_URI))

                .route(PAYMENT_MICROSERVICE, r -> r.path("/payment/**")
                        .filters(f->f.filter(filter))
                        .uri(PAYMENT_MICROSERVICE_URI))

                .route(ORDER_MICROSERVICE, r -> r.path("/order/**")
                        .uri(ORDER_MICROSERVICE_URI))

                .route(AUTH_MICROSERVICE, r -> r.path("/auth/**")
                        .filters(f->f.filter(filter))
                        .uri(AUTH_MICROSERVICE_URI))

                .build();
    }
}
