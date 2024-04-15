package com.example.userservice.service

import com.example.userservice.dto.TransactionRequestDto
import com.example.userservice.dto.TransactionResponseDto
import com.example.userservice.dto.TransactionStatus
import com.example.userservice.entity.UserTransaction
import com.example.userservice.repository.UserRepository
import com.example.userservice.repository.UserTransactionRepository
import com.example.userservice.util.EntityDtoUtil
import com.example.userservice.util.EntityDtoUtil.toDto
import com.example.userservice.util.EntityDtoUtil.toEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.function.Predicate


@Service
class TransactionService @Autowired constructor(
    private val userRepository: UserRepository,
    private val transactionRepository: UserTransactionRepository
) {
    fun createTransaction(requestDto: TransactionRequestDto): Mono<TransactionResponseDto> {
        return userRepository.updateUserBalance(requestDto.userId, requestDto.amount)
            .filter{it}
            .map { requestDto.toEntity() }
            .flatMap { transactionRepository.save(it) }
            .map {
                requestDto.toDto(TransactionStatus.APPROVED)
            }
            .defaultIfEmpty(requestDto.toDto(TransactionStatus.DECLINED))
    }

    fun getByUserId(userId: Int): Flux<UserTransaction> =
        transactionRepository.findByUserId(userId)

}

