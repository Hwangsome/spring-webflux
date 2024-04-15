package com.webflux.springwebflux.config;

import com.webflux.springwebflux.handler.HelloHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> route(HelloHandler handler) {
        return RouterFunctions
                .route(GET("/hello"), handler::hello);
    }
}