package com.example.productservice.util

import com.example.productservice.dto.ProductDto
import com.example.productservice.entity.Product
import reactor.core.publisher.Flux


object EntityDtoUtil {
    fun Product.toDto(): ProductDto =
         ProductDto(id, description, price)


    fun ProductDto.toEntity(): Product =
        Product(id, description, price)


}