package com.webflux.springwebflux.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public class HelloHandler {

    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse.ok().body(Mono.just("Hello, Spring WebFlux!").delayElement(Duration.ofSeconds(5)), String.class);
    }
}