package com.example.userservice.entity

import org.springframework.data.annotation.Id
import java.time.LocalDateTime

data class UserTransaction(
    @Id
    val id: Int? = null,
    val userId: Int,
    val amount: Int,
    val transactionDate: LocalDateTime
)
