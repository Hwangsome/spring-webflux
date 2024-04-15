package com.example.productservice.controller

import com.example.productservice.dto.ProductDto
import com.example.productservice.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@RestController
@RequestMapping("product")
class ProductController @Autowired constructor(private val productService: ProductService){

    @GetMapping("all")
    fun all(): Flux<ProductDto> = this.productService.getAll()


//    @GetMapping("price-range")
//    fun getByPriceRange(
//        @RequestParam("min") min: Int,
//        @RequestParam("max") max: Int
//    ): Flux<ProductDto> {
//        return this.productService.getProductByPriceRange(min, max)
//    }

    @GetMapping("{id}")
    fun getProductById(@PathVariable id: String): Mono<ResponseEntity<ProductDto>> {
//        this.simulateRandomException()
        return this.productService.getProductById(id)
            .map{ResponseEntity.ok(it)}
            .defaultIfEmpty(ResponseEntity.notFound().build())
    }

    @PostMapping
    fun insertProduct(@RequestBody productDtoMono: Mono<ProductDto>): Mono<ProductDto> {
        return this.productService.insertProduct(productDtoMono)
    }

    @PutMapping("{id}")
    fun updateProduct(
        @PathVariable id: String,
        @RequestBody productDtoMono: Mono<ProductDto>
    ): Mono<ResponseEntity<ProductDto>> {
        return this.productService.updateProduct(id, productDtoMono)
            .map{ResponseEntity.ok(it)}
            .defaultIfEmpty(ResponseEntity.notFound().build())
    }

    @DeleteMapping("{id}")
    fun deleteProduct(@PathVariable id: String): Mono<Void> {
        return this.productService.deleteProduct(id)
    }


}