package com.errami.mics.apigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class GatewayConfig {

    @Value("${car.service.url}")
    private String carServiceUrl;
    @Value("${carOption.service.url}")
    private String carOptionServiceUrl;
    @Value("${order.service.url}")
    private String orderServiceUrl;


    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("car_service", r -> r
                        .path("/api/car/**")
                        .uri(carServiceUrl))
                .route("order_service", r -> r
                        .path("/api/order/**")
                        .uri(orderServiceUrl))
                .route("order_service", r -> r
                        .path("/api/configuration/**")
                        .uri(orderServiceUrl))
                .route("car_option_service", r -> r
                        .path("/api/caroption/**")
                        .uri(carOptionServiceUrl))
                .build();
    }


/*
    @Bean
    public RouterFunction<ServerResponse>carserviceroute(HandlerFunctionAdapter handlerFunctionAdapter) {
        return GatewayRouterFunctions.route("car_service")
                .route(RequestPredicates.path("/api/car"),
                        HandlerFunctions.http(carOptionServiceUrl))
                .build();
    }

 */
}
