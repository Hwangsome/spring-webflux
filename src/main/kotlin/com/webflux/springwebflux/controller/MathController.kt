package com.webflux.springwebflux.controller

import com.webflux.springwebflux.dto.Response
import com.webflux.springwebflux.service.MathService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("math")
class MathController @Autowired constructor(private val mathService: MathService){

    @GetMapping("square/{input}")
    fun findSquare(@PathVariable input:Int):Response =
        if (input < 10)
            throw IllegalArgumentException()
        else mathService.findSquare(input)


    @GetMapping("table/{input}")
    fun multiplicationTable(@PathVariable input: Int): List<Response> {
        return mathService.multiplicationTable(input)
    }
}