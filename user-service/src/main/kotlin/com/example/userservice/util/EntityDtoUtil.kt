package com.example.userservice.util

import com.example.userservice.dto.TransactionRequestDto
import com.example.userservice.dto.TransactionResponseDto
import com.example.userservice.dto.TransactionStatus
import com.example.userservice.dto.UserDto
import com.example.userservice.entity.User
import com.example.userservice.entity.UserTransaction
import java.time.LocalDateTime

object EntityDtoUtil {

    fun User.toDto() = UserDto(this.id, this.name, this.balance)

    fun UserDto.toEntity() = User(this.id, this.name, this.balance)

    fun TransactionRequestDto.toEntity() = UserTransaction(userId = this.userId, amount = this.amount, transactionDate = LocalDateTime.now())

    fun TransactionRequestDto.toDto(transactionStatus: TransactionStatus) = TransactionResponseDto(this.userId, this.amount, transactionStatus)
}