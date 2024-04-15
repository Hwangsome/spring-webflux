package com.example.userservice.controller

import com.example.userservice.dto.TransactionRequestDto
import com.example.userservice.dto.TransactionResponseDto
import com.example.userservice.entity.UserTransaction
import com.example.userservice.service.TransactionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@RestController
@RequestMapping("user/transaction")
class UserTransactionController @Autowired constructor(private val transactionService: TransactionService) {

    @PostMapping
    fun createTransaction(@RequestBody requestDtoMono: Mono<TransactionRequestDto>): Mono<TransactionResponseDto> =
        requestDtoMono.flatMap { requestDto: TransactionRequestDto ->
            transactionService.createTransaction(
                requestDto
            )
        }


    @GetMapping
    fun getByUserId(@RequestParam("userId") userId: Int): Flux<UserTransaction> =
         transactionService.getByUserId(userId)
}