package com.example.productservice.entity

import org.springframework.data.annotation.Id

data class Product(
    @Id var id: String,
    val description: String,
    val price: Int
)
