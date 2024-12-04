package com.indra.api_gateway.routes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;

@Configuration
public class Routes {

    @Value("${maps.service.url}")
    private String mapsServiceUrl;
    @Value("${pricing.service.url}")
    private String pricingServiceUrl;
    @Value("${vehicles.service.url}")
    private String vehiclesServiceUrl;

    @Bean
    public RouterFunction<ServerResponse> mapsServiceRoute() {
        return GatewayRouterFunctions.route("maps")
                .route(RequestPredicates.path("/maps"), HandlerFunctions.http(mapsServiceUrl))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> mapsServiceSwaggerRoute() {
        return GatewayRouterFunctions.route("maps_service_swagger")
                .route(RequestPredicates.path("/aggregate/maps-service/v3/api-docs"), HandlerFunctions.http(mapsServiceUrl))
                .filter(setPath("/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> pricingServiceRoute() {
        return GatewayRouterFunctions.route("pricing_service")
                .route(RequestPredicates.path("/services/price"), HandlerFunctions.http(pricingServiceUrl))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> pricingServiceSwaggerRoute() {
        return GatewayRouterFunctions.route("pricing_service_swagger")
                .route(RequestPredicates.path("/aggregate/pricing-service/v3/api-docs"), HandlerFunctions.http(pricingServiceUrl))
                .filter(setPath("/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryServiceRoute() {
        return GatewayRouterFunctions.route("vehicles_service")
                .route(RequestPredicates.path("/cars*"), HandlerFunctions.http(vehiclesServiceUrl))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryServiceSwaggerRoute() {
        return GatewayRouterFunctions.route("vehicles_service_swagger")
                .route(RequestPredicates.path("/aggregate/vehicles-service/v3/api-docs"), HandlerFunctions.http(vehiclesServiceUrl))
                .filter(setPath("/api-docs"))
                .build();
    }

}