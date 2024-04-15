package com.example.userservice.controller

import com.example.userservice.dto.UserDto
import com.example.userservice.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@RestController
@RequestMapping("user")
class UserController @Autowired constructor(
    private val service: UserService
) {

    @GetMapping("all")
    fun all(): Flux<UserDto> {
        return service.all()
    }

    @GetMapping("{id}")
    fun getUserById(@PathVariable id: Int): Mono<ResponseEntity<UserDto>> =
         service.getUserById(id)
            .map { ResponseEntity.ok(it) }
            .defaultIfEmpty(ResponseEntity.notFound().build())


    @PostMapping
    fun createUser(@RequestBody userDtoMono: Mono<UserDto>): Mono<UserDto> =
         service.createUser(userDtoMono)


    @PutMapping("{id}")
    fun updateUser(@PathVariable id: Int, @RequestBody userDtoMono: Mono<UserDto>): Mono<ResponseEntity<UserDto>> =
         service.updateUser(id, userDtoMono)
            .map { ResponseEntity.ok(it) }
            .defaultIfEmpty(ResponseEntity.notFound().build())


    @DeleteMapping("{id}")
    fun deleteUser(@PathVariable id: Int): Mono<Void> = service.deleteUser(id)

}