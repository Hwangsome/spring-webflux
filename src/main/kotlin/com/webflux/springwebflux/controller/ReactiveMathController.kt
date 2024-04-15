package com.webflux.springwebflux.controller

import com.webflux.springwebflux.dto.MultiplyRequestDto
import com.webflux.springwebflux.dto.Response
import com.webflux.springwebflux.exception.InputValidationException
import com.webflux.springwebflux.service.ReactiveMathService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.SynchronousSink


@RestController
@RequestMapping("reactive-math")
class ReactiveMathController  @Autowired constructor(private val reactiveMathService: ReactiveMathService){


    @GetMapping("table/{input}/stream", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun multiplicationTableStream(@PathVariable input: Int): Flux<Response> {
        return reactiveMathService.multiplicationTable(input)
    }

    @GetMapping("table/{input}")
    fun multiplicationTable(@PathVariable input: Int): Flux<Response> {
        return reactiveMathService.multiplicationTable(input)
    }

    /**
     * {
     *     "first": 10,
     *     "second": 20
     * }
     */
    @PostMapping("multiply")
    fun multiply(
        @RequestBody requestDtoMono: Mono<MultiplyRequestDto>,
        @RequestHeader headers: Map<String, String>
    ): Mono<Response> {
        println("headers = $headers")
        return this.reactiveMathService.multiply(requestDtoMono)
    }

    @GetMapping("square/{input}/mono-error")
    fun monoError(@PathVariable input: Int): Mono<Response> {
        return Mono.just(input)
            .handle { integer, sink ->
                if (integer in 10..20) sink.next(integer)
                else sink.error(InputValidationException(integer))
            }
            .cast(Int::class.java)
            .flatMap { this.reactiveMathService.findSquare(it) }
    }

    @GetMapping("square/{input}/assignment")
    fun assignment(@PathVariable input: Int) =
        Mono.just(input)
            .filter{it in 10 .. 20}
            .flatMap { reactiveMathService.findSquare(it) }
            .map { ResponseEntity.ok(it) }
            .defaultIfEmpty(ResponseEntity.badRequest().build())

}