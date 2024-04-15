package com.example.productservice.config

import com.example.productservice.dto.ProductDto
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.core.publisher.Sinks

@Configuration
class SinkConfig {

    @Bean
    fun sink() = Sinks.many().replay().limit<ProductDto>(1)

    @Bean
    fun productBroadcast(sink: Sinks.Many<ProductDto>) = sink.asFlux()
}