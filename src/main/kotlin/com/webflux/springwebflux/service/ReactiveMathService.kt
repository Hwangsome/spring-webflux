package com.webflux.springwebflux.service

import com.webflux.springwebflux.dto.MultiplyRequestDto
import com.webflux.springwebflux.dto.Response
import com.webflux.springwebflux.utils.SleepUtil
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration
import java.util.stream.Collectors
import java.util.stream.IntStream

@Service
class ReactiveMathService {

    fun findSquare(input: Int): Mono<Response> = Mono.fromSupplier { input * input }.map { Response(output = it) }


    fun multiplicationTable(input: Int): Flux<Response> =
        Flux.range(1, 10)
            .delayElements(Duration.ofSeconds(1)) // 以非阻塞方式引入延迟
            .doOnNext { println("reactive-math-service processing : $it") }
            .map {
                println( Response(output = it * input))
                Response(output = it * input)
            }


//    fun multiplicationTable2(input: Int): Flux<Response> {
//        var list = IntStream.rangeClosed(1, 10)
//            .peek { SleepUtil.sleepSeconds(1) } // 模拟耗时操作
//            .peek { println("math-service processing : $it") }
//            .mapToObj { i -> Response(output = i * input) }
//            .collect(Collectors.toList())
//        return Flux.fromIterable(list);
//    }

    fun multiply(dtoMono: Mono<MultiplyRequestDto>) = dtoMono.map {
        it.first * it.second
    }.map {
        Response(output = it)
    }
}