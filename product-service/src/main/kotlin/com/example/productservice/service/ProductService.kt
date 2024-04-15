package com.example.productservice.service

import com.example.productservice.dto.ProductDto
import com.example.productservice.entity.Product
import com.example.productservice.repository.ProductRepository
import com.example.productservice.util.EntityDtoUtil
import com.example.productservice.util.EntityDtoUtil.toDto
import com.example.productservice.util.EntityDtoUtil.toEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.Sinks
import org.springframework.data.domain.Range;

@Service
class ProductService @Autowired constructor(
    private val productRepository:ProductRepository,
    private val sink: Sinks.Many<ProductDto>
) {

    fun getAll(): Flux<ProductDto> =
        productRepository.findAll()
            .map { it.toDto() }

    fun getProductById(id: String): Mono<ProductDto> =
        productRepository.findById(id)
            .map { it.toDto() }

    fun insertProduct(productDto: Mono<ProductDto>) =
        productDto.map { it.toEntity() }
            .flatMap { productRepository.insert(it) }
            .map { it.toDto() }
            .doOnNext{this.sink.tryEmitNext(it)}

    fun updateProduct(id: String, productDtoMono: Mono<ProductDto>) =
        productRepository.findById(id)
            .flatMap {
                productDtoMono.map {
                    it.toEntity()
                }.doOnNext {
                    it.id = id
                }
            }.flatMap {
                productRepository.save(it)
            }.map { it.toDto() }

//    fun getProductByPriceRange(min: Int, max: Int): Flux<ProductDto> {
//        return this.productRepository.findByPriceBetween(Range.closed(min, max))
//            .map { obj: EntityDtoUtil -> obj.toDto() }
//        this.productRepository.findby
//    }

    fun deleteProduct(id: String) = productRepository.deleteById(id)
}