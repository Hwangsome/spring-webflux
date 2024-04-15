package com.webflux.springwebflux.service

import com.webflux.springwebflux.dto.Response
import com.webflux.springwebflux.utils.SleepUtil
import org.springframework.stereotype.Service
import java.util.stream.Collectors
import java.util.stream.IntStream

@Service
class MathService {

    fun findSquare(input: Int): Response = Response(output = input * input)

    fun multiplicationTable(input: Int): List<Response> {
        return IntStream.rangeClosed(1, 10)
            .peek { SleepUtil.sleepSeconds(1) } // 模拟耗时操作
            .peek { println("math-service processing : $it") }
            .mapToObj { i -> Response(output = i * input) }
            .collect(Collectors.toList())

    }
}