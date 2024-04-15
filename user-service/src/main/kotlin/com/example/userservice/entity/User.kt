package com.example.userservice.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("users")
data class User(
    @Id var id: Int,
    val name: String,
    val balance: Int
)
